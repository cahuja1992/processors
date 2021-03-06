package org.clulab.dynet

import edu.cmu.dynet.ExpressionVector

/**
 * First layer that occurs in a sequence modeling architecture: goes from words to Expressions
 */
trait InitialLayer extends Saveable {
  def forward(sentence: AnnotatedSentence,
              predicatePosition: Option[Int] = None,
              doDropout: Boolean): ExpressionVector

  def outDim:Int // output dimension
}
