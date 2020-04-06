package org.clulab.lm

import com.typesafe.config.{Config, ConfigFactory}
import org.clulab.sequences.LstmUtils._
import org.clulab.struct.Counter
import org.clulab.utils.{Configured, Serializer}
import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source
import edu.cmu.dynet._
import edu.cmu.dynet.Expression._

import scala.collection.mutable
import FlairTrainer._
import org.clulab.fatdynet.utils.CloseableModelSaver
import org.clulab.fatdynet.utils.Closer.AutoCloser
import org.clulab.sequences.LstmUtils

import scala.collection.mutable.ArrayBuffer

/**
 * Implementation of the FLAIR language model
 */
class FlairTrainer( val c2i: Map[Char, Int],
                    val i2c: Array[Char],
                    val parameters: ParameterCollection,
                    val charLookupParameters: LookupParameter,
                    val charFwRnnBuilder: RnnBuilder,
                    val charBwRnnBuilder: RnnBuilder,
                    val fwO: Parameter,
                    val bwO: Parameter) {

  def mkTrainer(): Trainer = {
    val trainer = new RMSPropTrainer(parameters)
    trainer.clippingEnabled_=(true)
    trainer.clipThreshold_=(CLIP_THRESHOLD)
    trainer
  }

  /**
   * Trains the LM from the text in this file
   * The file must contain a sentence per line,
   *   with the white spaces between tokens normalized to a single space
   * @param trainFileName The name of the file with training sentences
   */
  def train( trainFileName:String,
             devFileName:Option[String],
             logCheckpoint:Int,
             saveCheckpoint:Int): Unit = {

    // initialize optimizer
    val trainer = mkTrainer()

    // train the fw and bw character LSTMs on all sentences in training
    val source = Source.fromFile(trainFileName)
    var sentCount = 0
    var cummulativeLoss = 0.0
    var numTagged = 0

    // start the first batch
    ComputationGraph.renew()
    var batchLosses = new ExpressionVector()

    for(sentence <- source.getLines()) {
      //println(s"Sent #$sentCount: $sentence")

      // prepare the chars in this sentence
      val characters = sentenceToCharacters(sentence)

      //
      // left-to-right prediction
      //
      val fwIn = characters
      val fwEmissionScores = emissionScoresAsExpressions(fwIn, charFwRnnBuilder, fwO, doDropout = true)
      val fwLoss = languageModelLoss(fwEmissionScores, fwIn)
      batchLosses.add(fwLoss)

      //
      // right-to-left prediction
      //
      val bwIn = characters.reverse
      val bwEmissionScores = emissionScoresAsExpressions(bwIn, charBwRnnBuilder, bwO, doDropout = true)
      val bwLoss = languageModelLoss(bwEmissionScores, bwIn)
      batchLosses.add(bwLoss)

      //
      // book keeping
      //
      sentCount += 1
      numTagged += characters.length + 1

      //
      // backprop
      // we do this only when the batch is full
      //
      if(batchLosses.size >= BATCH_SIZE) {
        val comboLoss = sum(batchLosses) / batchLosses.size
        cummulativeLoss += comboLoss.value().toFloat()
        ComputationGraph.backward(comboLoss)
        safeUpdate(trainer, parameters)

        // report perplexity if a dev file is available
        if(sentCount % saveCheckpoint == 0 && devFileName.nonEmpty){
          reportPerplexity(devFileName.get)
        }

        // reset for the next batch
        ComputationGraph.renew()
        batchLosses = new ArrayBuffer[Expression]()
        //println("Renewed graph!")
      }

      //
      // reporting and model saving
      //
      if(sentCount % logCheckpoint == 0) {
        logger.debug(s"Processed $sentCount sentences. Cummulative loss: ${cummulativeLoss / numTagged}.")

        // save a model every 50K sentences
        if(sentCount % saveCheckpoint == 0){
          val baseModelName = s"flair_s$sentCount"
          save(baseModelName)
        }
      }
    }
    source.close()
  }

  /** Prepare the chars in this sentence */
  def sentenceToCharacters(sentence:String): Array[Char] = {
    val charBuffer = new ArrayBuffer[Char]()
    for(i <- sentence.indices) {
      val c = sentence.charAt(i)
      if(c2i.contains(c))
        charBuffer += c
      else
        charBuffer += UNKNOWN_CHAR
    }
    charBuffer.toArray
  }

  def reportPerplexity(devFileName: String): Unit = {
    val source = Source.fromFile(devFileName)
    var sentCount = 0
    var cummulativeFwPerplexity = 0.0
    var cummulativeBwPerplexity = 0.0
    logger.debug("Computing perplexity in dev...")
    for(sentence <- source.getLines()) {
      val characters = sentenceToCharacters(sentence)
      ComputationGraph.renew()

      val fwIn = characters
      val fwEmissionScores = emissionScoresAsExpressions(fwIn, charFwRnnBuilder, fwO) // no dropout during testing!
      val fwPp = perplexity(fwEmissionScores, fwIn)

      val bwIn = characters.reverse
      val bwEmissionScores = emissionScoresAsExpressions(bwIn, charBwRnnBuilder, bwO)
      val bwPp = perplexity(bwEmissionScores, bwIn)

      cummulativeFwPerplexity += fwPp
      cummulativeBwPerplexity += bwPp
      sentCount += 1
    }
    source.close()
    logger.info(s"Average forward perplexity: ${cummulativeFwPerplexity / sentCount.toDouble}")
    logger.info(s"Average backward perplexity: ${cummulativeBwPerplexity / sentCount.toDouble}")
  }

  /**
   * Updates the model, catching vanishing/exploding gradients and trying to recover
   * @param myTrainer Optimizer
   * @param parameters Model
   */
  def safeUpdate(myTrainer: Trainer, parameters: ParameterCollection): Unit = {
    try {
      myTrainer.update()
    } catch {
      case exception: RuntimeException if exception.getMessage.startsWith("Magnitude of gradient is bad") =>
        // aim to reset the gradient and continue training
        parameters.resetGradient()
        logger.info(s"Caught an invalid gradient exception: ${exception.getMessage}. Reset gradient L2 norm to: ${parameters.gradientL2Norm()}")
    }
  }

  /**
   * Gets the gold tag id for the character at position i
   * @param characters Array of chars in this sentence
   * @param i Position in the character array
   * @return The id of the gold tag (i.e., the next character) for this character
   */
  def goldTagId(characters:Array[Char], i:Int): Int = {
    val goldTid = c2i(
      if(i < characters.length - 1) characters(i + 1) // the next character if it exists
      else EOS_CHAR
    )
    goldTid
  }

  /** Computes perplexity for this sentence */
  def perplexity(emissionScoresForSeq: ExpressionVector, characters: Array[Char]): Double = {
    var pp = 1.0
    for(i <- emissionScoresForSeq.indices) {
      val goldTid = goldTagId(characters, i)
      val prob = pick(softmax(emissionScoresForSeq(i)), goldTid)
      pp *= math.pow(1.0 / prob.value().toFloat(), 1.0 / characters.length.toDouble)
    }
    pp
  }

  /** Greedy loss function, ignoring transition scores */
  def languageModelLoss(emissionScoresForSeq:ExpressionVector,
                        characters:Array[Char]): Expression = {

    val goldLosses = new ExpressionVector()

    for(i <- emissionScoresForSeq.indices) {
      val goldTid = goldTagId(characters, i)

      // emissionScoresForSeq(i) = all tag emission scores for the word at position i
      goldLosses.add(pickNegLogSoftmax(emissionScoresForSeq(i), goldTid))
    }

    sum(goldLosses)
  }

  def emissionScoresAsExpressions(chars: Array[Char],
                                  rnnBuilder: RnnBuilder,
                                  pO:Parameter,
                                  doDropout:Boolean = false): ExpressionVector = {
    val embeddings = chars.map(mkEmbedding)

    if(doDropout) {
      rnnBuilder.setDropout(DROPOUT_PROB)
    } else {
      rnnBuilder.disableDropout()
    }

    val states = transduce(embeddings, rnnBuilder)

    val O = parameter(pO)
    val emissionScores = new ExpressionVector()
    for(s <- states) {
      emissionScores.add(O * s)
    }

    emissionScores
  }

  def mkEmbedding(c:Char): Expression = {
    val charEmbedding =
      if(c2i.contains(c)) {
        // found the character in the known vocabulary
        lookup(charLookupParameters, c2i(c))
      } else {
        // not found; return the embedding at position 0, which is reserved for unknown words
        lookup(charLookupParameters, UNKNOWN_CHAR)
      }

    charEmbedding
  }

  def save(modelFilename: String): Unit = {
    val dynetFilename = mkDynetFilename(modelFilename)
    val x2iFilename = mkX2iFilename(modelFilename)

    new CloseableModelSaver(dynetFilename).autoClose { modelSaver =>
      modelSaver.addModel(parameters, "/flair")
    }

    Serializer.using(LstmUtils.newPrintWriter(x2iFilename)) { printWriter =>
      val dim = charLookupParameters.dim().get(0)

      LstmUtils.saveCharMap(printWriter, c2i, "c2i")
      LstmUtils.save(printWriter, dim, "dim")
    }
  }
}

class FlairConfig(config:Config) extends Configured {
  override def getConf: Config = config
}

object FlairTrainer {
  val logger:Logger = LoggerFactory.getLogger(classOf[FlairTrainer])

  val CHAR_RNN_LAYERS = 1
  val CHAR_EMBEDDING_SIZE = 100
  val CHAR_RNN_STATE_SIZE = 2048
  val CLIP_THRESHOLD = 5.0f
  val MIN_UNK_FREQ_RATIO = 0.000001

  val DROPOUT_PROB:Float = 0.2.toFloat

  val UNKNOWN_CHAR:Char = 0.toChar // placeholder for unknown characters
  val EOS_CHAR:Char = 1.toChar // virtual character indicating beginning of sentence

  val BATCH_SIZE = 1

  def apply(baseModelFilename: String): FlairTrainer = {
    load(baseModelFilename)
  }

  def mkParams(c2i: Map[Char, Int]): FlairTrainer = {
    val i2c = fromIndexToChar(c2i)

    val parameters = new ParameterCollection()
    val charLookupParameters = parameters.addLookupParameters(c2i.size, Dim(CHAR_EMBEDDING_SIZE))

    val charFwBuilder = new GruBuilder(CHAR_RNN_LAYERS, CHAR_EMBEDDING_SIZE, CHAR_RNN_STATE_SIZE, parameters)
    val charBwBuilder = new GruBuilder(CHAR_RNN_LAYERS, CHAR_EMBEDDING_SIZE, CHAR_RNN_STATE_SIZE, parameters)

    val fwO = parameters.addParameters(Dim(c2i.size, CHAR_RNN_STATE_SIZE))
    val bwO = parameters.addParameters(Dim(c2i.size, CHAR_RNN_STATE_SIZE))

    new FlairTrainer(c2i, i2c, parameters,
      charLookupParameters, charFwBuilder, charBwBuilder,
      fwO, bwO)
  }

  def load(baseModelFilename: String): FlairTrainer = {
    logger.debug(s"Loading Flair model from $baseModelFilename...")
    val dynetFilename = mkDynetFilename(baseModelFilename)
    val x2iFilename = mkX2iFilename(baseModelFilename)

    //
    // load the .x2i info
    //
    val (c2i, _) = Serializer.using(LstmUtils.newSource(x2iFilename)) { source =>
      val byLineCharMapBuilder = new LstmUtils.ByLineCharIntMapBuilder()
      val lines = source.getLines()
      val c2i = byLineCharMapBuilder.build(lines)
      val dim = new LstmUtils.ByLineIntBuilder().build(lines)
      (c2i, dim)
    }
    logger.debug(s"Loaded a character map with ${c2i.keySet.size} entries.")

    //
    // create the DyNet parameters
    //
    val model = mkParams(c2i)

    //
    // load the above parameters from the DyNet model file
    //
    LstmUtils.loadParameters(dynetFilename, model.parameters, key = "/flair")

    model
  }

  protected def generateKnownCharacters(trainFileName: String): (Set[Char], Int) = {
    logger.debug(s"Counting characters in file $trainFileName...")
    val counts = new Counter[Char]()
    val source = Source.fromFile(trainFileName)
    var sentCount = 0
    for(line <- source.getLines()) {
      for(c <- line.toCharArray) {
        counts.incrementCount(c)
      }
      sentCount += 1
    }
    source.close()
    logger.debug("Counting completed.")
    logger.debug(s"Found ${counts.size} characters.")
    var totalCounts = 0.0
    for(c <- counts.keySet) {
      totalCounts += counts.getCount(c)
    }
    val knownChars = new mutable.HashSet[Char]()
    for(c <- counts.keySet) {
      if(counts.getCount(c) > totalCounts * MIN_UNK_FREQ_RATIO) {
        knownChars += c
      }
    }
    logger.debug(s"Found ${knownChars.size} not unknown characters.")
    logger.debug(s"Known characters: ${knownChars.toSeq.sorted.mkString(", ")}")

    // add the virtual characters we need
    knownChars += UNKNOWN_CHAR
    knownChars += EOS_CHAR

    (knownChars.toSet, sentCount)
  }

  def main(args: Array[String]): Unit = {
    initializeDyNet() // autoBatch = true, mem = "512")
    val configName = "flair-en"
    val config = new FlairConfig(ConfigFactory.load(configName))

    //
    // test mode
    //
    if(config.contains("flair.test.model")) {
      logger.debug("Entering evaluation mode...")
      val lm = FlairTrainer(config.getArgString("flair.test.model", None))
      lm.reportPerplexity(config.getArgString("flair.train.dev", None))
    }

    //
    // train mode
    //
    else {
      logger.debug("Entering training mode...")

      // build the set of known characters
      val trainFileName = config.getArgString("flair.train.train", None)
      val (knownChars, totalSentCount) = generateKnownCharacters(trainFileName)
      val c2i = knownChars.toArray.zipWithIndex.toMap

      val lm = mkParams(c2i)
      lm.train(
        trainFileName,
        Some(config.getArgString("flair.train.dev", None)),
        config.getArgInt("flair.train.logCheckpoint", Some(1000)),
        config.getArgInt("flair.train.saveCheckpoint", Some(50000))
      )
    }
  }
}
