package org.gameflow.entity

import java.util.ArrayList
import org.gameflow.util.ListenableCollection
import java.util.Collection
import org.gameflow.RenderContext
import java.util.concurrent.ConcurrentSkipListSet
import java.util.Collections
import org.gameflow.component.Component

/**
 * A group of entities.
 */
public open class EntityGroup {

    private val entities = ConcurrentSkipListSet<Entity>()
    private val unmodifiableEntities: Collection<Entity> = Collections.unmodifiableCollection<Entity>(entities) !!

    public final fun entities() : Collection<Entity> {
        return unmodifiableEntities
    }

    public final fun add(entity: Entity)  {
        entities.add(entity)
        onEntityAdded(entity)
    }

    public final fun remove(entity: Entity)  {
        entities.remove(entity)
        onEntityRemoved(entity)
    }

    public final fun clear() {
        onAllEntitiesRemoved(unmodifiableEntities)
        entities.clear()
    }


    protected open fun onEntityAdded(entity: Entity) {}
    protected open fun onEntityRemoved(entity: Entity) {}
    protected open fun onAllEntitiesRemoved(entities: Collection<Entity>) {}

}