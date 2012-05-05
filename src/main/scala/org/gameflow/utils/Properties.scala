package org.gameflow.utils

import java.util.{Collections, LinkedHashMap}
import scala.Symbol


/**
 * Provides simple property getters and setters.
 */
trait Properties {

  private var _properties: java.util.Map[Symbol, Any] = null

  def get[T](name: Symbol): T = {
    if (_properties == null || !_properties.containsKey(name)) throw new Error("Unknown property '"+name.name+"'")
    else _properties.get(name).asInstanceOf[T]
  }

  def get[T](name: Symbol, default: T): T = {
    if (_properties == null || !_properties.containsKey(name)) default
    else _properties.get(name).asInstanceOf[T]
  }

  def set[T](name: Symbol, value: T) {
    if (_properties == null) _properties = new LinkedHashMap()
    _properties.put(name, value)
  }

  def has(name: Symbol): Boolean = _properties.containsKey(name)

  def apply[T](name: Symbol): T = get(name)
  def update[T](name: Symbol, value: T) = set(name, value)

  def properties: java.util.Map[Symbol, Any] = {
    if (_properties == null) Collections.emptyMap[Symbol, Any]()
    else Collections.unmodifiableMap[Symbol, Any](_properties)
  }
}