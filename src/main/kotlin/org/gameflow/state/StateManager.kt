package org.gameflow.state

import org.gameflow.Game
import java.util.Collection

/**
 * 
 */
public trait StateManager {

    val currentGameState: GameState?
    fun states(): Collection<GameState>
    fun addState(gameState: GameState)
    fun removeState(gameState: GameState)
    fun changeGameState(newStateName: String)


    fun doGameStateUpdate(game: Game)

}