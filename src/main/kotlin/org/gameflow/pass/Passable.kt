package org.gameflow.pass

import java.util.Collection

/**
 * Something that can be processed by an entity pass.
 */
public trait Passable {

    fun

    fun containedPassables(): Iterator<out Passable>

}