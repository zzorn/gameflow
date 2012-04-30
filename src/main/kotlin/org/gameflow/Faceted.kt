package org.gameflow

import java.util.List
import java.util.ArrayList
import java.util.Set
import java.util.HashSet
import java.util.LinkedHashSet
import org.gameflow.util.ListenableCollection

/**
 * 
 */
public trait Faceted<T>: Updating, Rendering {

    public val facets: ListenableCollection<Component<T>>

    public final fun addFacet(facet: Component<T>) {
        facets.add(facet)
    }

    public final fun removeFacet(facet: Component<T>) {
        facets.remove(facet)
    }

    protected final fun updateFacets(seconds : Double) {
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


    override fun render(r : RenderContext) {
        renderFacets(r)
    }

    override fun update(seconds : Double) {
        updateFacets(seconds)
    }
}
