package org.gameflow.java2D.physics

import org.gameflow.core.entity.Component
import org.gameflow.utils.Vec3

/**
 *
 */
trait Location extends Component {

  def pos: Vec3

  def velocity: Vec3 = Vec3.Zero

  def update(durationSeconds: Double) {}
}