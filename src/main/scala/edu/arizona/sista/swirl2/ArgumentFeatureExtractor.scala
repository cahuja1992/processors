package edu.arizona.sista.swirl2

import edu.arizona.sista.embeddings.word2vec.Word2Vec
import edu.arizona.sista.processors.Sentence
import edu.arizona.sista.struct.{Counter, DirectedGraph}

import ArgumentFeatureExtractor._

/**
 * Creates features for the binary classification of tokens as potential arguments
 * User: mihais
 * Date: 7/14/15
 */
class ArgumentFeatureExtractor(word2vecFile:String) {

  lazy val w2v = new Word2Vec(word2vecFile)

  def addEmbeddingsFeatures(features:Counter[String],
                            prefix:String,
                            sent:Sentence,
                            position:Int): Unit = {
    val embeddings = w2v.getWordVector(sent.words(position))
    if(embeddings.isDefined)
    for(i <- embeddings.get.indices) {
      val fn = prefix + ":" + i.toString
      val fv = embeddings.get(i)
      features.setCount(fn, fv)
    }
  }

  var lemmaCounts:Option[Counter[String]] = None

  def addDepFeatures(features:Counter[String],
                     prefix:String,
                     sent:Sentence,
                     deps:DirectedGraph[String],
                     arg:Int,
                     pred:Int): Unit = {
    val predTag = tagAt(sent, pred, MAX_TAG_SIZE)
    val argTag = tagAt(sent, arg, MAX_TAG_SIZE)
    val argLemma = lemmaAt(sent, arg)
    val predLemma = lemmaAt(sent, pred)
    val before = if(arg < pred) "T" else "F"
    val paths = deps.shortestPathEdges(pred, arg, ignoreDirection = true)
    paths.foreach(path => {
      // path including POS tags
      val pst = pathToString(path, sent, useTags = true)
      features.incrementCount(s"path$prefix:$before:$predTag-$pst")

      // path including lemmas along the way
      val psl = pathToString(path, sent, useTags = false)
      features.incrementCount(s"path$prefix:$before:$predLemma-$psl")
    })
  }

  def pathToString(path:Seq[(Int, Int, String, String)], sent:Sentence, useTags:Boolean = true):String = {
    if(useTags)
      path.map(d => s"${d._3}${d._4}${tagAt(sent, endPoint(d), MAX_TAG_SIZE)}").mkString("-")
    else
      path.map(d => s"${d._3}${d._4}${lemmaAt(sent, endPoint(d))}").mkString("-")
  }

  def endPoint(dep:(Int, Int, String, String)):Int = {
    dep._4 match {
      case ">" => dep._2
      case _ => dep._1
    }
  }

  def mkFeatures(sent:Sentence, position:Int, pred:Int):Counter[String] = {
    val features = new Counter[String]

    val predLemma = lemmaAt(sent, pred)
    val predTag = tagAt(sent, pred, MAX_TAG_SIZE)

    if(position == pred) {
      features.incrementCount(s"same:$predLemma:$predTag")
      return features
    }

    val argTag = tagAt(sent, position, MAX_TAG_SIZE)
    val before = position < pred

    addDepFeatures(features, "B", sent, sent.stanfordBasicDependencies.get, position, pred)
    addDepFeatures(features, "C", sent, sent.stanfordCollapsedDependencies.get, position, pred)

    // unigrams
    for (i <- Range(-1, 2)) {
      val lemma = lemmaAt(sent, position + i)
      val tag = tagAt(sent, position + i)

      // of lemmas
      features.incrementCount(s"lemma:$i:$lemma")
      features.incrementCount(s"lemma:$i:$lemma:$before")
      features.incrementCount(s"lemma:$i:$lemma:$predLemma")
      features.incrementCount(s"lemma:$i:$lemma:$predLemma:$before")

      // hybrid
      features.incrementCount(s"lemma:$i:$lemma:$predTag")
      features.incrementCount(s"lemma:$i:$lemma:$predTag:$before")
      features.incrementCount(s"lemma:$i:$tag:$predLemma")
      features.incrementCount(s"lemma:$i:$tag:$predLemma:$before")


      // of POS tags
      features.incrementCount(s"tag:$i:$tag")
      features.incrementCount(s"tag:$i:$tag:$before")
      features.incrementCount(s"tag:$i:$tag:$predTag")
      features.incrementCount(s"tag:$i:$tag:$predTag:$before")
    }

    addEmbeddingsFeatures(features, "AE", sent, position)

    /*
    val deps = sent.stanfordBasicDependencies.get
    //if("IN|TO".r.findFirstMatchIn(lemmaAt(sent, position)).isDefined) {
      for(dep <- deps.outgoingEdges(position)) {
        val mlemma = lemmaAt(sent, dep._1)
        val mtag = tagAt(sent, dep._1)
        features += s"mlemma:$mlemma"
        features += s"mtag:$mtag"
      }
    //}

    val paths = deps.shortestPathEdges(pred, position, ignoreDirection = true)
    if(paths.nonEmpty) {
      val path = paths.head.toArray
      features += s"path-length:${path.length}"
      //val pathLabels = path.map(_._3).mkString("-")
      //features += s"path-labels:$pathLabels"
      //features += s"path-labels:$predLemma-$pathLabels-${lemmaAt(sent, position)}"
      // println(s"$position\t$pred\t${path.toList}\t$pathLabels")

      val dirPathLabels = path.map(d => s"${d._3}${d._4}").mkString("-")
      features += s"path-labels:$dirPathLabels"
      features += s"path-labels:$predLemma-$dirPathLabels-${lemmaAt(sent, position)}"
      features += s"path-labels:$predTag-$dirPathLabels-${tagAt(sent, position)}"
    } else {
      features += "no-path"
    }

    if (pred == position) {
      features += s"same-token:${tagAt(sent, position)}"
    } else {
      features += s"token-dist:${math.abs(pred - position)}:${pred < position}"
      if(pred < position) features += "before"
      else features += "after"
    }
    */

    features
  }

  def wordAt(sent:Sentence, position:Int):String = {
    if(position >= 0 && position < sent.size) {
      val w = sent.words(position)
      val l = sent.lemmas.get(position)
      if(lemmaCounts.isDefined) {
        if(lemmaCounts.get.getCount(l) > UNKNOWN_THRESHOLD) {
          w
        } else {
          UNKNOWN_TOKEN
        }
      } else {
        w
      }
    }
    else PADDING
  }
  def lemmaAt(sent:Sentence, position:Int):String = {
    if(position >= 0 && position < sent.size) {
      val l = sent.lemmas.get(position)
      if(lemmaCounts.isDefined) {
        if(lemmaCounts.get.getCount(l) > UNKNOWN_THRESHOLD) {
          l
        } else {
          UNKNOWN_TOKEN
        }
      } else {
        l
      }
    }
    else PADDING
  }
  def tagAt(sent:Sentence, position:Int, maxSize:Int = 0):String = {
    if(position >= 0 && position < sent.size) {
      val t = sent.tags.get(position)
      if(maxSize > 0 && maxSize < t.length) t.substring(0, maxSize)
      else t
    }
    else PADDING
  }
}

object ArgumentFeatureExtractor {
  val PADDING = "##"

  val MAX_TAG_SIZE = 2
  val UNKNOWN_THRESHOLD = 5
  val UNKNOWN_TOKEN = "*u*"
}
