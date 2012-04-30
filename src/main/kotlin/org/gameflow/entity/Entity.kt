package org.gameflow.entity

import org.gameflow.Updating
import org.gameflow.Rendering
import org.gameflow.Faceted
import org.gameflow.util.ListenableCollection
import org.gameflow.Component
import org.gameflow.RenderContext

/**
 * A game object, consisting of components.
 */
public class Entity {

    private val components = ListenableCollection<Component>()

    public final fun addComponent(component: Component) {
        components.add(component)
    }

    public final fun removeComponent(component: Component) {
        components.remove(component)
    }

    private final fun updateFacets(seconds : Double) {
        facets.updateCollection()

        for (facet in facets.elements()) {
            facet.update(seconds)
        }
    }

    protected final fun renderFacets(r : RenderContext) {
        for (facet in facets.elements()) {
            facet.render(r)
        }
    }


    final override fun update(seconds : Double) {
        updateFacets(seconds)
        onUpdate(seconds)
    }

    final override fun render(r : RenderContext) {
        renderFacets(r)
        onRender(r)
    }

    protected fun onUpdate(seconds : Double)

    protected fun onRender(r : RenderContext)
}