package org.gameflow.core.entity

/**
 *
 */
trait Component {

  /**Called when this component is added to an entity. */
  def onAdded(host: Entity) {}

  /**Called when this component is removed from an entity. */
  def onRemoved(host: Entity) {}

}