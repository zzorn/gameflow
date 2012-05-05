package org.gameflow.core.pass

import org.gameflow.core.entity.{Entity, Component}
import org.gameflow.core.Game


/**
 * A pass that just handles a specific component
 */
abstract class ComponentPass[T <: Component](componentName: Symbol) extends Pass {

  final def handleEntity(entity: Entity, game: Game) {
    val comp: T = entity.getComponent[T](componentName)
    if (comp != null) handleComponent(comp, entity, game)
  }

  protected def handleComponent(component: T, entity: Entity, game: Game)
}