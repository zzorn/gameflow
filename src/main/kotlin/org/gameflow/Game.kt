package org.gameflow

import java.util.List
import java.util.Map
import java.util.HashMap
import org.gameflow.state.GameState
import org.gameflow.util.ListenableCollection

/**
 * 
 */
// TODO: When 'This' is supported, remove G from constructor and replace uses of G with This
public open class Game<G: Game<G>>() : Faceted<G>, Updating, Rendering {

    private val gameStates: Map<String, GameState<G>> = HashMap<String, GameState<G>>()
    private var activeGameState: GameState<G>? = null
    private var newGameStateName: String? = null
    private var stopped = false

    public override val facets : ListenableCollection<Component<G>> = ListenableCollection<Component<G>>()

    fun addState(gameState: GameState<G>) {
        gameStates.put(gameState.name, gameState)
    }

    fun removeState(gameState: GameState<G>) {
        gameStates.remove(gameState.name)
    }

    fun changeGameState(newStateName: String) {
        newGameStateName = newStateName
    }

    /** Calls init, then starts the game loop.  Call stop to exit it. */
    public final fun start() {
        while(!stopped) {
            doLoop()
        }
    }

    /** Runs through the game loop once. */
    public final fun doLoop() {

    }

    public final fun doUpdate(seconds: Double) {
        updateGameState(seconds)
        updateFacets(seconds)
        update(seconds)
    }

    public final fun doRender(r : RenderContext) {
        renderGameState(r)
        renderFacets(r)
        render(r)
    }

    override fun update(seconds : Double) {}
    override fun render(r : RenderContext) {}

    private fun renderGameState(r: RenderContext) {
        // Render current game state if present
        activeGameState?.render(r)
    }

    private fun updateGameState(seconds : Double) {
        // Check if game state changed
        if (newGameStateName != null) {
            // Get new game state, null the change variable in case the onExit or onEnter methods immediately change the game state again.
            val newStateName = newGameStateName
            newGameStateName = null

            // Switch to new game state
            val gameState = gameStates.get(newStateName)
            if (gameState == null) throw Exception("No game state named '$newStateName' found.")
            else {
                activeGameState?.onExit(this as G)
                activeGameState = gameState
                activeGameState?.onEnter(this as G)
            }
        }

        // Update current game state
        activeGameState?.update(seconds)
    }


}