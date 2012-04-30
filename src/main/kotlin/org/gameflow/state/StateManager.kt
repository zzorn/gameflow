package org.gameflow.state

import org.gameflow.Game

/**
 * 
 */
public trait StateManager {

    val currentGameState: GameState?
    fun addState(gameState: GameState)
    fun removeState(gameState: GameState)
    fun changeGameState(newStateName: String)

    fun doGameStateUpdate(game: Game)

}