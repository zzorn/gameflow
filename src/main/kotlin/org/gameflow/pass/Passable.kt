package org.gameflow.pass

import java.util.Collection
import org.gameflow.component.Componentized

/**
 * Something that can be processed by an entity pass.
 */
public trait Passable {

    fun doPass(pass: Pass) {
        if (this is Componentized) pass.apply(this)


        pass.
    }

    fun

}