package org.gameflow.core.state

import java.util.concurrent.ConcurrentHashMap
import org.gameflow.core.pass.{Passable, Pass}
import scala.collection.JavaConversions._
import org.gameflow.core.{Manager, LifeCycled, Game}


/**
 * Keeps track of game states, of which one is active at a time.
 */
final class GameStateManager extends Passable with Manager {

  private val gameStates = new ConcurrentHashMap[Symbol, GameState]()
  private var newGameStateName: Symbol = null

  private var _currentGameState : GameState = null

  def currentGameState = _currentGameState


  def addState(gameState: GameState) {
    gameStates.put(gameState.name, gameState)
    if (initialized) gameState.init(game)
  }

  def removeState(gameState: GameState) {
    gameStates.remove(gameState.name)
    if (initialized) gameState.deInit(game)
  }

  def changeGameState(newStateName: Symbol) {
    newGameStateName = newStateName
  }

  def states: java.util.Collection[GameState] = gameStates.values()


  def doGameStateUpdate(game : Game) {
    // Check if game state changed
    if (newGameStateName != null) {

      // Get new game state, null the change variable in case the onExit or onEnter methods immediately change the game state again.
      val newStateName = newGameStateName
      newGameStateName = null

      // Switch to new game state
      val gameState = gameStates.get(newStateName)
      if (gameState == null) throw new Exception("No game state named '"+newGameStateName.name+"' found.")
      else {
        if (_currentGameState != null) _currentGameState.onExit(game)
        _currentGameState = gameState
        if (_currentGameState != null) _currentGameState.onEnter(game)
      }
    }
  }


  override protected def onInit(game: Game) {
    gameStates.values.foreach{_.init(game)}
  }

  override protected def onDeInit(game: Game) {
    gameStates.values.foreach{_.deInit(game)}
  }

  override def doPassOnChildren(pass : Pass, game: Game) {
    if (_currentGameState != null) _currentGameState.doPass(pass, game)
  }

  override def update(game: Game) {
    doGameStateUpdate(game)
  }
}
