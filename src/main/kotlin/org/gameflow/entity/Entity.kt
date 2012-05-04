package org.gameflow.entity

import java.util.concurrent.ConcurrentHashMap
import org.gameflow.pass.Passable
import org.gameflow.component.Component

/**
 * A game object, consisting of components.
 */
public trait Entity : Passable {

    private val components: ConcurrentHashMap<Class<Component>, Component>

    public fun addComponent(component: Component): Entity {
        if ((components.get(component.javaClass)?:null) != component) {
            components.put(component.javaClass, component)
            onComponentAdded(component)
            component.onAdded(this)
        }
        return this
    }

    public fun removeComponent(component: Component): Entity {
        if ((components.get(component.javaClass)?:null) == component) {
            components.remove(component.javaClass)
            onComponentRemoved(component)
            component.onRemoved(this)
        }
        return this
    }

    public fun getComponent<T: Component>(componentType: Class<T>): T? {
        return components.get(componentType) as? T
    }

    // Overloaded operators
    public fun plus(component: Component): Entity = addComponent(component)
    public fun minus(component: Component): Entity = removeComponent(component)
    public fun get<T: Component>(componentType: Class<T>): T? = getComponent(componentType)
    public fun set<T: Component>(componentType: Class<T>, component: T) {addComponent(component)}

    // Listeners, can be overridden
    protected open fun onComponentAdded(component: Component) {}
    protected open fun onComponentRemoved(component: Component) {}
}
