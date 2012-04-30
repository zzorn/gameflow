package org.gameflow.entity

import java.util.ArrayList
import org.gameflow.util.ListenableCollection
import java.util.Collection
import org.gameflow.Faceted
import org.gameflow.Component
import org.gameflow.RenderContext
import java.util.concurrent.ConcurrentSkipListSet
import java.util.Collections

/**
 * A group of entities.
 */
public class EntityGroup : Component {

    private val entities = ConcurrentSkipListSet<Entity>()
    private val unmodifiableEntities: Collection<Entity> = Collections.unmodifiableCollection<Entity>(entities) !!

    public fun entities() : Collection<Entity> {
        return unmodifiableEntities
    }

    public fun add(entity: Entity)  {
        entities.add(entity)
    }

    public fun remove(entity: Entity)  {
        entities.remove(entity)
    }

    public fun clear() {
        entities.clear()
    }


}