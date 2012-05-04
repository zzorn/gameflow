package org.gameflow.entity

import java.util.Collection
import org.gameflow.pass.Passable

/**
 * A set of named groups of entities.
 */
public trait EntityGroups: Passable {

    public fun groups(): Collection<EntityGroup>
    public fun createGroup(name: String) : EntityGroup
    public fun getGroup(name: String) : EntityGroup
    public fun containsGroup(name: String): Boolean

}