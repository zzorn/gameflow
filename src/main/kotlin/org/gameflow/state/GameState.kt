package org.gameflow.state

import org.gameflow.Updating
import org.gameflow.Game
import org.gameflow.Rendering


/**
 * 
 */
public trait GameState<G: Game<G>> : Updating, Rendering {

    val name: String
    val game: G

    fun onEnter(game: G)
    fun onExit(game: G)

}