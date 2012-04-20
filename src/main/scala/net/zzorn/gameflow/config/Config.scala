package net.zzorn.gameflow.config

import collection.SortedMap
import java.awt.Color


/**
 *
 */
trait Config {

  var entries: SortedMap[String, AnyRef] = SortedMap()

  def get[T](name: String): T = {
    entries(name).asInstanceOf[T]
  }

  def set[T](name: String, value: T) {
    entries += name -> value
  }

}
