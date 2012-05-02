package org.gameflow.component

import java.util.concurrent.ConcurrentHashMap
import java.util.Collection
import org.gameflow.pass.Passable
import java.util.Collections

/**
 * 
 */
public abstract class ComponentizedBase(): Componentized {

    private val components = ConcurrentHashMap<Class<Component>, Component>()

    override fun containedPassables() : Collection<Passable> = Collections.emptyList<Passable>() !!
}
