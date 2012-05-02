package org.gameflow.pass

import org.gameflow.entity.Entity
import org.gameflow.state.GameState
import org.gameflow.Game
import org.gameflow.component.Componentized
import org.gameflow.entity.EntityGroup
import java.util.Collection
import org.gameflow.entity.EntityGroups


/**
 * Something that processes all entities matching specified criteria each game loop, or at some other intervals.
 */
trait EntityPass {

    open fun shouldApply(passable: Passable) : Boolean = true

    fun startPass()
    fun apply(componentized: Componentized)
    fun endPass()

    fun runPasses(passables: Iterator<out Passable>) {
        while (passables.hasNext) {
            runPass(passables.next())
        }
    }

    fun runPass(passable: Passable) {
        if (passable is Componentized && shouldApply(passable)) apply(passable)

        runPasses(passable.containedPassables())
    }
}