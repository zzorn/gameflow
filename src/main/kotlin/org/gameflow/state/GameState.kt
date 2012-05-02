package org.gameflow.state

import org.gameflow.Updating
import org.gameflow.Game
import org.gameflow.Rendering
import org.gameflow.component.Componentized
import org.gameflow.entity.EntityGroups


/**
 * 
 */
public trait GameState: Componentized {

    val entityGroups: EntityGroups

    val name: String

    fun onEnter(game: Game)
    fun onExit(game: Game)

}