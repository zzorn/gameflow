package org.gameflow.java2D.physics

import org.gameflow.core.entity.Entity
import org.gameflow.core.Game
import org.gameflow.core.pass.{ComponentPass, Pass}

/**
 *
 */
class PhysicsPass extends ComponentPass[Location]('Location) {

  private var duration: Double = 0

  override def startPass(game: Game) {
    duration = game.clock.frameDurationSeconds
  }

  protected def handleComponent(location: Location, entity: Entity, game: Game) {
    location.update(duration)
  }
}