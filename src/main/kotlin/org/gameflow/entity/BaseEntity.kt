package org.gameflow.entity

import java.util.concurrent.ConcurrentHashMap
import org.gameflow.component.Component

/**
 * 
 */
public open class BaseEntity: Entity {

    private val components = ConcurrentHashMap<Class<Component>, Component>()

}