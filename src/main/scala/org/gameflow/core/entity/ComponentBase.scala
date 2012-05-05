package org.gameflow.core.entity


/**
 *
 */
class ComponentBase extends Component {

  private var _entity: Entity = null

  def entity: Entity = _entity

  final override def onAdded(host: Entity) {
    _entity = host
    onAdded()
  }

  final override def onRemoved(host: Entity) {
    onRemoved()
    _entity = null
  }

  protected def onAdded() {}
  protected def onRemoved() {}
}