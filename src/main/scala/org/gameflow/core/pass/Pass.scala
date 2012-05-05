package org.gameflow.core.pass

import org.gameflow.core.entity.Entity
import org.gameflow.core.{LifeCycled, Game}


/**
 * Something that processes all entities matching specified criteria each game loop, or at some other intervals.
 */
trait Pass extends LifeCycled with Comparable[Pass] {

  def startPass(game: Game) {}

  def handleEntity(entity: Entity, game: Game)

  def endPass(game: Game) {}

  def compareTo(o: Pass): Int = {
    val selfHash = hashCode()
    val otherHash = o.hashCode()
    if (selfHash < otherHash) -1
    else if (selfHash > otherHash) 1
    else 0
  }
}