package org.gameflow.core

import clock.{FixedDelayClock, Clock}
import entity.EntityGroupsImpl
import pass.{PassManager, Pass, Passable}
import java.util.ArrayList
import scala.collection.JavaConversions._
import state.GameStateManager
import org.gameflow.utils.ParameterChecker

/**
 *
 */
class Game(val clock: Clock = new FixedDelayClock()) extends Passable with LifeCycled {
  ParameterChecker.requireNotNull(clock, 'clock)

  private val managers = new ArrayList[LifeCycled]()

  private var started = false
  private var stopped = false

  val passes = addManager(new PassManager())
  val states = addManager(new GameStateManager())
  val groups = addManager(new EntityGroupsImpl())

  def main(args: Array[String]) {
    start()
  }

  /** Calls init, then starts the game loop.  Call stop to exit it. */
  final def start() {
    if (!started) {
      started = true
      managers.foreach(_.init(this))
      init(this)

      while (!stopped) {
        doLoop()
      }

      deInit(this)
      managers.reverse.foreach(_.deInit(this))
    }
  }

  /** Causes the game to stop after the current run through the main loop finishes */
  final def stop() {
    stopped = true
  }

  final def isRunning = started && !stopped

  final def isStarted = started

  final def isStopped = stopped

  /** Runs through the game loop once. */
  final def doLoop() {
    // Update time
    clock.tick()

    // Change game state if needed
    states.doGameStateUpdate(this)

    // Run passes in order
    passes.runPasses(this)
  }

  override def doPassOnChildren(pass: Pass, game: Game) {
    groups.doPass(pass, game)
    states.doPass(pass, game)
  }

  protected final def addManager[T <: LifeCycled](manager: T): T = {
    managers.add(manager)
    manager
  }


}
