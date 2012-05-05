package org.gameflow.gameflow.config

import collection.SortedMap
import collection.immutable.TreeMap
import java.util.HashMap


/**
 *
 */
trait Config {

  var entries: java.util.Map[Symbol, AnyRef] = new HashMap[Symbol, AnyRef]()

  def get[T <: AnyRef](name: Symbol): T = {
    entries.get(name).asInstanceOf[T]
  }

  def set[T <: AnyRef](name: Symbol, value: T) {
    entries.put(name, value)
  }

}
