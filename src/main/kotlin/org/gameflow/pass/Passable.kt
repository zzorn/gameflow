package org.gameflow.pass

import java.util.Collection
import org.gameflow.entity.Entity
import org.gameflow.Game

/**
 * Something that can be processed by an entity pass.
 */
public trait Passable {

    fun doPass(pass: Pass, game: Game) {
        if (this is Entity) pass.apply(this, game)

        doPassOnChildren(pass, game)
    }

    fun doPassOnChildren(pass: Pass, game: Game) {}

}