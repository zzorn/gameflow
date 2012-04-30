package org.gameflow.state

import org.gameflow.Updating
import org.gameflow.Game
import org.gameflow.Rendering
import org.gameflow.component.Componentized


/**
 * 
 */
public trait GameState: Componentized {

    val name: String

    fun onEnter(game: Game)
    fun onExit(game: Game)

}