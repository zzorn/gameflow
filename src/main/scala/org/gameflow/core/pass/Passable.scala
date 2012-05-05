package org.gameflow.core.pass

import org.gameflow.core.Game
import org.gameflow.core.entity.Entity


/**
 * Something that can be processed by an entity pass.
 */
trait Passable {

  final def doPass(pass: Pass, game: Game) {
    this match {
      case entity: Entity => pass.handleEntity(entity, game)
      case _ => // Skip
    }

    doPassOnChildren(pass, game)
  }

  def doPassOnChildren(pass: Pass, game: Game) {}

}