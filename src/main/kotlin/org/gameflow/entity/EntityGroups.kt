package org.gameflow.entity

import java.util.Collection
import org.gameflow.pass.Passable

/**
 * 
 */
public trait EntityGroups: Passable {

    public fun groups(): Collection<EntityGroup>
    public fun createGroup(name: String) : EntityGroup
    public fun getGroup(name: String) : EntityGroup
    public fun containsGroup(name: String): Boolean

    override fun containedPassables() : Iterator<out Passable> = groups().iterator() !!

}