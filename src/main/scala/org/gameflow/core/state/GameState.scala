package org.gameflow.core.state

import org.gameflow.core.entity.{EntityGroupsImpl, EntityGroups}
import org.gameflow.core.pass.Passable
import org.gameflow.core.{LifeCycled, Game}


/**
 *
 */
trait GameState extends Passable with LifeCycled {

  def name: Symbol

  val groups: EntityGroups = new EntityGroupsImpl()

  def onEnter(game: Game) {}
  def onExit(game: Game) {}

}