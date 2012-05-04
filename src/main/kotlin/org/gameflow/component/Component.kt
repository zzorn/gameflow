package org.gameflow.component

import org.gameflow.entity.Entity

/**
 * 
 */
public trait Component {

    /** Called when this component is added to an entity. */
    fun onAdded(host: Entity) {}

    /** Called when this component is removed from an entity. */
    fun onRemoved(host: Entity) {}

}