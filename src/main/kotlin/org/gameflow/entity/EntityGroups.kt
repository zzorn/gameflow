package org.gameflow.entity

/**
 * 
 */
public trait EntityGroups {

    public fun iterator(): java.util.Iterator<EntityGroup>
    public fun create(name: String) : EntityGroup
    public fun get(name: String) : EntityGroup
    public fun contains(name: String): Boolean
}