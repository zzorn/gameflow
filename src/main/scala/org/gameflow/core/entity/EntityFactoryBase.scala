package org.gameflow.core.entity

/**
 *
 */
trait EntityFactoryBase extends EntityFactory {

  final def createEntity(): Entity = {
    val entity = new Entity()
    populateEntity(entity)
    entity
  }

  protected def populateEntity(entity: Entity)
}