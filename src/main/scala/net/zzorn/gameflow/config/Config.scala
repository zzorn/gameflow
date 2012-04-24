package net.zzorn.gameflow.config

import collection.SortedMap
import java.awt.Color


/**
 *
 */
trait Config {

  var entries: SortedMap[String, AnyRef] = SortedMap()

  def get[T <: AnyRef](name: String): T = {
    entries(name).asInstanceOf[T]
  }

  def set[T <: AnyRef](name: String, value: T) {
    entries += name -> value
  }

}
