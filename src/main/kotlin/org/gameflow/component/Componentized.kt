package org.gameflow.component

import java.util.concurrent.ConcurrentHashMap
import org.gameflow.pass.Passable

/**
 * 
 */
public trait Componentized: Passable {

    private val components: ConcurrentHashMap<Class<Component>, Component>

    public final fun addComponent(component: Component): Componentized {
        components.put(component.javaClass, component)
        onComponentAdded(component)
        return this
    }

    public final fun removeComponent(component: Component): Componentized {
        components.remove(component)
        onComponentRemoved(component)
        return this
    }

    public final fun getComponent<T: Component>(componentType: Class<T>): T? {
        return components.get(componentType) as? T
    }

    // Overloaded operators
    public final fun plus(component: Component): Componentized = addComponent(component)
    public final fun minus(component: Component): Componentized = removeComponent(component)
    public final fun get<T: Component>(componentType: Class<T>): T? = getComponent(componentType)
    public final fun set<T: Component>(componentType: Class<T>, component: T) {addComponent(component)}

    // Listeners, can be overridden
    protected open fun onComponentAdded(component: Component) {}
    protected open fun onComponentRemoved(component: Component) {}
}
