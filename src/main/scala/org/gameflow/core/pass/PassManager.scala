package org.gameflow.core.pass

import java.util.concurrent.ConcurrentSkipListSet
import scala.collection.JavaConversions._
import org.gameflow.core.{Game, LifeCycled}
import java.util.{ArrayList, Collections}

/**
 * Keeps track of passes, and initializes them.
 */
class PassManager extends LifeCycled {

  private val _passes = new ArrayList[Pass]()

  override def onInit(game: Game) {
    _passes.foreach{ _.init(game) }
  }

  def addPass(pass: Pass) {
    if (!_passes.contains(pass)) {
      _passes.add(pass)
      if (initialized) pass.init(game)
    }
  }

  def removePass(pass: Pass) {
    if (_passes.contains(pass)) {
      _passes.remove(pass)
      if (initialized) pass.deInit(game)
    }
  }

  override def onDeInit(game: Game) {
    _passes.foreach{ _.deInit(game) }
  }

  def hasPass(pass: Pass): Boolean = _passes.contains(pass)
  def passes: java.util.Collection[Pass] = Collections.unmodifiableCollection(_passes)


  def runPasses(game: Game) {
    // Run passes in order
    _passes.foreach{pass =>
      pass.startPass(game)

      // Apply pass to game and all sub-components
      game.doPass(pass, game)

      pass.endPass(game)
    }
  }
}