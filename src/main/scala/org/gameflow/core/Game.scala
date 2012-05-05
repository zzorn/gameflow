package org.gameflow.core

import clock.{FixedDelayClock, Clock}
import entity.EntityGroupsImpl
import pass.{PassManager, Pass, Passable}
import scala.collection.JavaConversions._
import state.GameStateManager
import org.gameflow.utils.ParameterChecker
import java.util.{Collections, ArrayList}

/**
 * Root game class.  Does not have any managers pre-added, usually you want to use a more specific derived class of this.
 */
class Game extends Passable {

  private val _managers = new java.util.ArrayList[Manager]()

  private var initialized = false
  private var stopped = false
  private var running = false


  // Lifecycle state
  final def isRunning = running
  final def isInitialized = initialized
  final def isStopped = stopped


  // Main method

  /**
   * Default main method that calls start, provided for convenience in case this class is overridden by an object.
   */
  def main(args: Array[String]) {
    start()
  }


  // Main loop

  /**
   * Calls init, then starts the game loop.  Call stop to exit it.
   * A game can only be started once.
   * Calls de-init when loop ends.
   */
  final def start() {
    init()
    runLoop()
    deInit()
  }

  /**
   * Causes the game to stop after the current run through the main loop finishes
   */
  final def stop() {
    stopped = true
  }



  // Managers

  final def addManager[T <: Manager](manager: T): T = {
    _managers.add(manager)

    if (initialized) manager.init(this)

    manager
  }

  final def managers: java.util.List[Manager] = Collections.unmodifiableList(_managers)


  // Passable

  override final def doPassOnChildren(pass: Pass, game: Game) {
    _managers.foreach(_.doPass(pass, game))
  }


  // Over-rideable init and update

  /** Called before managers are initialized, can be used for adding any necessary managers or doing other early setup work. */
  protected def setup() {}

  /** Called after managers have been created and initialized, can be used for setting up game content. */
  protected def onInit() {}

  /** Called each update tick. */
  protected def onUpdate() {}

  /** Called when main loop has been exited and application is about to quit, but before managers have been de-initialized. */
  protected def onDeInit() {}


  // Game lifecycle and loop parts

  protected final def init() {
    if (initialized) throw new Error("Game '" + this + "' has already been initialized.")

    setup()
    _managers.foreach(_.init(this))
    onInit()

    initialized = true
  }

  protected final def runLoop() {
    if (!initialized) throw new Error("Game '" + this + "' has not yet been initialized.")

    running = true
    while (!stopped) {
      loopOnce()
    }
    running = false
  }

  /** Runs through the game loop once. */
  protected final def loopOnce() {
    if (!initialized) throw new Error("Game '" + this + "' has not yet been initialized.")

    _managers.foreach(_.update(this))

    onUpdate()
  }

  protected final def deInit() {
    if (!initialized) throw new Error("Game '" + this + "' has not yet been initialized.")
    if (!stopped) throw new Error("Game '" + this + "' has not yet been stopped.")

    onDeInit()
    _managers.toList.reverse.foreach(_.deInit(this)) // De-initialize in reverse order of initialization.
  }


}
