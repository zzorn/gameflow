package org.gameflow.pass

import org.gameflow.entity.Entity
import org.gameflow.state.GameState
import org.gameflow.Game
import org.gameflow.component.Componentized


/**
 * Something that processes all entities matching specified criteria each game loop, or at some other intervals.
 */
trait EntityPass {

    fun applyToGroup(name: String) : Boolean
    fun applyToEntity(entity: Entity) : Boolean
    fun applyToState(state: GameState) : Boolean
    fun applyToGame(game: Game) : Boolean

    fun startPass()
    fun apply(componentized: Componentized)
    fun endPass()
}