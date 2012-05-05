package org.gameflow.core


/**
 *
 */
trait LifeCycled {

  private var _initialized = false
  private var _deInitialized = false
  private var _game: Game = null

  final def initialized: Boolean = _initialized

  final def deInitialized: Boolean = _initialized

  /**
   * @return The game that this object belongs to, or null if it has not been initialized yet.
   */
  final def game: Game = _game

  /**Called once, when the component or game is initializing. */
  final def init(game: Game) {
    if (deInitialized) throw new Error("The class " + this + " has been de-initialized, can not re-initialize")
    else if (initialized) throw new Error("The class " + this + " is already initialized, can not re-initialize")
    else {
      _game = game
      onInit(game)
      _initialized = true
    }
  }

  /**Called at most once, when the component has already been initialized. */
  final def deInit(game: Game) {
    println("DeInitializing " + this)
    if (!initialized) throw new Error("The class " + this + " is not yet initialized, can not de-initialize")
    else if (deInitialized) throw new Error("The class " + this + " is already deInitialized, can not re-de-initialize")
    else {
      onDeInit(game)
      _deInitialized = true
      _game = null
    }
  }

  protected def onInit(game: Game) {}

  protected def onDeInit(game: Game) {}

}