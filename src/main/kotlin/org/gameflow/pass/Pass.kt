package org.gameflow.pass

import org.gameflow.entity.Entity
import org.gameflow.state.GameState
import org.gameflow.Game
import org.gameflow.entity.EntityGroup
import java.util.Collection
import org.gameflow.entity.EntityGroups


/**
 * Something that processes all entities matching specified criteria each game loop, or at some other intervals.
 */
trait Pass {

    fun init(game: Game)
    fun deInit(game: Game)

    fun startPass(game: Game)
    fun apply(entity: Entity, game: Game)
    fun endPass(game: Game)

}