package org.clulab.dynet

import java.io.PrintWriter

import edu.cmu.dynet.{ComputationGraph, Expression, ExpressionVector, ParameterCollection}
import org.clulab.struct.Counter
import org.clulab.utils.Configured
import org.clulab.dynet.Utils._

import scala.collection.mutable.ArrayBuffer

/**
 * A sequence of layers that implements a complete NN architecture for sequence modeling
 */
class Layers (val initialLayer: Option[InitialLayer],
              val intermediateLayers: IndexedSeq[IntermediateLayer],
              val finalLayer: Option[FinalLayer]) extends Saveable {

  def outDim: Option[Int] = {
    if(finalLayer.nonEmpty) {
      return Some(finalLayer.get.outDim)
    }

    if(intermediateLayers.nonEmpty) {
      return Some(intermediateLayers.last.outDim)
    }

    if(initialLayer.nonEmpty) {
      return Some(initialLayer.get.outDim)
    }

    None
  }

  override def toString: String = {
    val sb = new StringBuilder
    var started = false
    if(initialLayer.nonEmpty) {
      sb.append("initial = " + initialLayer.get)
      started = true
    }
    for(i <- intermediateLayers.indices) {
      if(started) sb.append(" ")
      sb.append(s"intermediate (${i + 1}) = " + intermediateLayers(i))
      started = true
    }
    if(finalLayer.nonEmpty) {
      if(started) sb.append(" ")
      sb.append("final = " + finalLayer.get)
    }
    sb.toString()
  }

  def isEmpty: Boolean = initialLayer.isEmpty && intermediateLayers.isEmpty && finalLayer.isEmpty
  def nonEmpty: Boolean = ! isEmpty

  // strictly for testing purposes (doDropout = false for all layers)
  protected def forward(sentence: AnnotatedSentence,
                        predicatePosition: Option[Int] = None): ExpressionVector = {
    if(initialLayer.isEmpty) {
      throw new RuntimeException(s"ERROR: you can't call forward() on a Layers object that does not have an initial layer: $toString!")
    }

    var states = initialLayer.get.forward(sentence, predicatePosition, doDropout = false)

    for (i <- intermediateLayers.indices) {
      states = intermediateLayers(i).forward(states, doDropout = false)
    }

    if(finalLayer.nonEmpty) {
      states = finalLayer.get.forward(states, predicatePosition, doDropout = false)
    }

    states
  }

  protected def forwardFrom(inStates: ExpressionVector,
                            predicatePosition: Option[Int] = None): ExpressionVector = {
    if(initialLayer.nonEmpty) {
      throw new RuntimeException(s"ERROR: you can't call forwardFrom() on a Layers object that has an initial layer: $toString!")
    }

    var states = inStates

    for (i <- intermediateLayers.indices) {
      states = intermediateLayers(i).forward(states, doDropout = false)
    }

    if(finalLayer.nonEmpty) {
      states = finalLayer.get.forward(states, predicatePosition, doDropout = false)
    }

    states
  }

  override def saveX2i(printWriter: PrintWriter): Unit = {
    if(initialLayer.nonEmpty) {
      save(printWriter, 1, "hasInitial")
      initialLayer.get.saveX2i(printWriter)
    } else {
      save(printWriter, 0, "hasInitial")
    }

    save(printWriter, intermediateLayers.length, "intermediateCount")
    for(il <- intermediateLayers) {
      il.saveX2i(printWriter)
    }

    if(finalLayer.nonEmpty) {
      save(printWriter, 1, "hasFinal")
      finalLayer.get.saveX2i(printWriter)
    } else {
      save(printWriter, 0, "hasFinal")
    }
  }
}

object Layers {
  def apply(config: Configured,
            paramPrefix: String,
            parameters: ParameterCollection,
            wordCounter: Counter[String],
            labelCounterOpt: Option[Counter[String]],
            hasPredicate: Boolean,
            providedInputSize: Option[Int]): Layers = {
    val initialLayer = EmbeddingLayer.initialize(config, paramPrefix + ".initial", parameters, wordCounter)

    var inputSize =
      if(initialLayer.nonEmpty) {
        Some(initialLayer.get.outDim)
      } else if(providedInputSize.nonEmpty) {
        providedInputSize
      } else {
        None
      }

    val intermediateLayers = new ArrayBuffer[IntermediateLayer]()
    var done = false
    for(i <- 1 to MAX_INTERMEDIATE_LAYERS if ! done) {
      if(inputSize.isEmpty) {
        throw new RuntimeException("ERROR: trying to construct an intermediate layer without a known input size!")
      }
      val intermediateLayer = RnnLayer.initialize(config, paramPrefix + s".intermediate$i", parameters, inputSize.get)
      if(intermediateLayer.nonEmpty) {
        intermediateLayers += intermediateLayer.get
        inputSize = Some(intermediateLayer.get.outDim)
      } else {
        done = true
      }
    }

    val finalLayer =
      if(labelCounterOpt.nonEmpty) {
        if(inputSize.isEmpty) {
          throw new RuntimeException("ERROR: trying to construct a final layer without a known input size!")
        }

        ForwardLayer.initialize(config, paramPrefix + ".final", parameters,
          labelCounterOpt.get, hasPredicate, inputSize.get)
      } else {
        None
      }

    new Layers(initialLayer, intermediateLayers, finalLayer)
  }

  val MAX_INTERMEDIATE_LAYERS = 10

  def loadX2i(parameters: ParameterCollection, lines: BufferedIterator[String]): Layers = {
    val byLineIntBuilder = new ByLineIntBuilder()

    val hasInitial = byLineIntBuilder.build(lines, "hasInitial")
    val initialLayer =
      if(hasInitial == 1) {
        val layer = EmbeddingLayer.load(parameters, lines)
        //println("loaded initial layer!")
        Some(layer)
      } else {
        None
      }

    val intermediateLayers = new ArrayBuffer[IntermediateLayer]()
    val intermCount = byLineIntBuilder.build(lines, "intermediateCount")
    for(_ <- 0 until intermCount) {
      val il = RnnLayer.load(parameters, lines)
      //println("loaded one intermediate layer!")
      intermediateLayers += il
    }

    val hasFinal = byLineIntBuilder.build(lines, "hasFinal")
    val finalLayer =
      if(hasFinal == 1) {
        val layer = ForwardLayer.load(parameters, lines)
        //println("loaded final layer!")
        Some(layer)
      } else {
        None
      }

    new Layers(initialLayer, intermediateLayers, finalLayer)
  }

  def predictJointly(layers: IndexedSeq[Layers],
                     sentence: AnnotatedSentence): IndexedSeq[IndexedSeq[String]] = {
    val labelsPerTask = new ArrayBuffer[IndexedSeq[String]]()

    DyNetSync.synchronized { // DyNet's computation graph is a static variable, so this block must be synchronized
      ComputationGraph.renew()

      // layers(0) contains the shared layers
      if(layers(0).nonEmpty) {
        val sharedStates = layers(0).forward(sentence)

        for (i <- 1 until layers.length) {
          val states = layers(i).forwardFrom(sharedStates)
          val emissionScores: Array[Array[Float]] = Utils.emissionScoresToArrays(states)
          val labels = layers(i).finalLayer.get.inference(emissionScores)
          labelsPerTask += labels
        }
      }

      // no shared layer
      else {
        for (i <- 1 until layers.length) {
          val states = layers(i).forward(sentence)
          val emissionScores: Array[Array[Float]] = Utils.emissionScoresToArrays(states)
          val labels = layers(i).finalLayer.get.inference(emissionScores)
          labelsPerTask += labels
        }
      }
    }

    labelsPerTask
  }

  private def forwardForTask(layers: IndexedSeq[Layers],
                             taskId: Int,
                             sentence: AnnotatedSentence,
                             predPositionOpt: Option[Int] = None): ExpressionVector = {
    //
    // make sure this code is:
    //   (a) called inside a synchronized block, and
    //   (b) called after the computational graph is renewed (see predict below for correct usage)
    //

    val states = {
      // layers(0) contains the shared layers
      if (layers(0).nonEmpty) {
        val sharedStates = layers(0).forward(sentence, predPositionOpt)
        layers(taskId + 1).forwardFrom(sharedStates, predPositionOpt)
      }

      // no shared layer
      else {
        layers(taskId + 1).forward(sentence, predPositionOpt)
      }
    }

    states
  }

  def predict(layers: IndexedSeq[Layers],
              taskId: Int,
              sentence: AnnotatedSentence,
              predPositionOpt: Option[Int] = None): IndexedSeq[String] = {
    val labelsForTask =
      DyNetSync.synchronized { // DyNet's computation graph is a static variable, so this block must be synchronized
        ComputationGraph.renew()

        val states = forwardForTask(layers, taskId, sentence, predPositionOpt)
        val emissionScores: Array[Array[Float]] = Utils.emissionScoresToArrays(states)
        layers(taskId + 1).finalLayer.get.inference(emissionScores)
      }

    labelsForTask
  }

  def loss(layers: IndexedSeq[Layers],
           taskId: Int,
           sentence: AnnotatedSentence,
           goldLabels: IndexedSeq[String],
           predicatePosition: Option[Int] = None): Expression = {
    val states = forwardForTask(layers, taskId, sentence, predicatePosition)
    layers(taskId + 1).finalLayer.get.loss(states, goldLabels)
  }
}
