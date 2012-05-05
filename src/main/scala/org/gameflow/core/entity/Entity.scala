package org.gameflow.core.entity

import org.gameflow.core.pass.Passable
import java.util.concurrent.ConcurrentHashMap
import org.gameflow.utils.{Properties, ParameterChecker}
import scala.collection.JavaConversions._

/**
 * A game object, consisting of components.
 */
class Entity extends Passable with Properties with Comparable[Entity] {

  private val components = new ConcurrentHashMap[Symbol, Component]()
/*
  def addComponent(component: Component): Entity = {
    addComponent(Symbol(component.getClass.getSimpleName), component)
  }
*/
  def addComponent(name: Symbol, component: Component): Entity = {
    ParameterChecker.requireNotNull(component, 'component)

    if (components.get(component.getClass) != component) {
      components.put(name, component)
      onComponentAdded(component)
      component.onAdded(this)
    }

    this
  }

  def removeComponent(name: Symbol): Entity = {
    if (components.containsKey(name)) {
      val comp = components.get(name)
      components.remove(name)
      onComponentRemoved(comp)
      comp.onRemoved(this)
    }

    this
  }

  def getComponentOfType[T <: Component](implicit manifest: Manifest[T]): T = {
    components.get(Symbol(manifest.erasure.getSimpleName)).asInstanceOf[T]
  }

  def getComponent[T <: Component](name: Symbol): T = {
    components.get(name).asInstanceOf[T]
  }

  def withComponentOfType[T <: Component](block: T => Unit)(implicit manifest: Manifest[T]) {
    withComponent(Symbol(manifest.erasure.getSimpleName), block)
  }

  def withComponent[T <: Component](name: Symbol, block: T => Unit) {
    val component: T = components.get(name).asInstanceOf[T]
    if (component != null) block(component)
  }

  // Overloaded operators
//  def += (component: Component): Entity = addComponent(component)

  // Listeners, can be overridden
  protected def onComponentAdded(component: Component) {}
  protected def onComponentRemoved(component: Component) {}

  def compareTo(o: Entity): Int = {
    val selfHash = hashCode()
    val otherHash = o.hashCode()
    if (selfHash < otherHash) -1
    else if (selfHash > otherHash) 1
    else 0
  }

  override def toString: String = {
    "Entity { components = " + components.mkString(", ") + "}"
  }
}
