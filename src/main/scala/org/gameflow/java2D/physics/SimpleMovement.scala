package org.gameflow.java2D.physics

import org.gameflow.utils.Vec3
import org.gameflow.core.clock.Clock

/**
 *
 */
class SimpleMovement(x: Double = 0, y: Double = 0, z: Double = 0) extends Location {

  override val pos = Vec3(x, y, z)
  override val velocity = Vec3()
  val thrust = Vec3()

  override def update(durationSeconds: Double) {
    velocity +*= (thrust, durationSeconds)
    pos      +*= (velocity, durationSeconds)
  }



}