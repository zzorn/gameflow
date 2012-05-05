package org.gameflow.core.entity

import java.util.HashMap
import org.gameflow.core.pass.Pass
import scala.collection.JavaConversions._
import org.gameflow.core.{Manager, Game}

/**
 *
 */
class EntityGroupsImpl extends EntityGroups {
  private val entityGroups = new HashMap[Symbol, EntityGroup]()

  override def groups(): java.util.Collection[EntityGroup] = entityGroups.values()

  override def createGroup(name: Symbol): EntityGroup = {
    if (entityGroups.containsKey(name)) throw new Error("An EnityGroup named '" + name.name + "' already exists!")
    val group = new EntityGroup(name)
    entityGroups.put(name, group)
    group
  }

  override def getGroup(name: Symbol): EntityGroup = {
    val group = entityGroups.get(name)
    if (group == null) throw new Error("No EntityGroup named '" + name.name + "' found!")
    else group
  }

  override def containsGroup(name: Symbol): Boolean = entityGroups.containsKey(name)


  override def doPassOnChildren(pass: Pass, game: Game) {
    entityGroups.values.foreach{_.doPass(pass, game)}
  }
}