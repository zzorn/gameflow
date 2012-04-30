package org.gameflow.state

import org.gameflow.Game

/**
 * 
 */
public abstract class BaseGameState<G: Game<G>>(override val name: String, override val game: G): GameState<G> {
    {
        // Automatically add on construction - is this a good idea?
        game.addState(this)
    }

}