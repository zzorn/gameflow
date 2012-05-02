package org.gameflow.state

import org.gameflow.state.GameState
import java.util.concurrent.ConcurrentHashMap
import org.gameflow.Game
import java.util.Collection

/**
 * Keeps track of game states, of which one is active at a time.
 */
public class StateManagerImpl(): StateManager {

    private val gameStates = ConcurrentHashMap<String, GameState>()
    private var newGameStateName: String? = null

    public override var currentGameState : GameState? = null
        private set

    override fun addState(gameState: GameState) {
        gameStates.put(gameState.name, gameState)
    }

    override fun removeState(gameState: GameState) {
        gameStates.remove(gameState.name)
    }

    override fun changeGameState(newStateName: String) {
        newGameStateName = newStateName
    }

    override fun states(): Collection<GameState> = gameStates.values() !!;


    override fun doGameStateUpdate(game : Game) {
        // Check if game state changed
        if (newGameStateName != null) {
            // Get new game state, null the change variable in case the onExit or onEnter methods immediately change the game state again.
            val newStateName = newGameStateName
            newGameStateName = null

            // Switch to new game state
            val gameState = gameStates.get(newStateName)
            if (gameState == null) throw Exception("No game state named '$newStateName' found.")
            else {
                currentGameState?.onExit(game)
                currentGameState = gameState
                currentGameState?.onEnter(game)
            }
        }
    }


}
