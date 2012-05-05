package org.gameflow.core.entity

import java.util.concurrent.ConcurrentSkipListSet
import java.util.Collections
import org.gameflow.core.pass.Passable
import org.gameflow.core.pass.Pass
import org.gameflow.core.Game
import scala.collection.JavaConversions._

/**
 *
 */
class EntityGroup(val name: Symbol) extends Passable {

  private val _entities = new ConcurrentSkipListSet[Entity]()
  private val unmodifiableEntities = Collections.unmodifiableCollection[Entity](_entities)

  final def entities : java.util.Collection[Entity] = {
    unmodifiableEntities
  }

  final def add(entity: Entity) {
    _entities.add(entity)
    onEntityAdded(entity)
  }

  final def remove(entity: Entity) {
    _entities.remove(entity)
    onEntityRemoved(entity)
  }

  final def clear() {
    onAllEntitiesRemoved(unmodifiableEntities)
    _entities.clear()
  }

  protected def onEntityAdded(entity: Entity) {}
  protected def onEntityRemoved(entity: Entity) {}
  protected def onAllEntitiesRemoved(entities: java.util.Collection[Entity]) {}

  override final def doPassOnChildren(pass : Pass, game : Game) {
    _entities.foreach {_.doPass(pass, game)}
  }

}