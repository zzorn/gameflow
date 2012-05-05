package org.gameflow.core.entity

import org.gameflow.core.pass.Passable
import org.gameflow.core.LifeCycled

/**
 *
 */
trait EntityGroups extends Passable with LifeCycled {

  def groups(): java.util.Collection[EntityGroup]
  def createGroup(name: Symbol) : EntityGroup
  def getGroup(name: Symbol) : EntityGroup
  def containsGroup(name: Symbol): Boolean
}