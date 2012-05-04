package org.gameflow.state

import org.gameflow.Updating
import org.gameflow.Game
import org.gameflow.Rendering
import org.gameflow.entity.EntityGroups
import org.gameflow.pass.Pass
import org.gameflow.entity.Entity


/**
 * 
 */
public trait GameState: Entity {

    val entityGroups: EntityGroups

    val name: String

    fun onEnter(game: Game)
    fun onExit(game: Game)

}