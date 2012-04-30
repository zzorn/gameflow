package org.gameflow.component

import java.util.concurrent.ConcurrentHashMap

/**
 * 
 */
public abstract class ComponentizedBase(): Componentized {

    private val components = ConcurrentHashMap<Class<Component>, Component>()
}
