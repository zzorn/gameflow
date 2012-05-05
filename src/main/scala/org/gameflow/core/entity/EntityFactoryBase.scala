package org.gameflow.core.entity

/**
 *
 */
trait EntityFactoryBase extends EntityFactory {

  final def createEntity(): Entity = {
    val entity = createEntityInstance
    populateEntity(entity)
    entity
  }


  protected def createEntityInstance: Entity = {
    new Entity()
  }

  protected def populateEntity(entity: Entity)
}