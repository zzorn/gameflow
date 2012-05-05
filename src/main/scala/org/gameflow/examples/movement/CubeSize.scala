package org.gameflow.examples.movement

import org.gameflow.core.clock.Clock
import java.awt.Color
import org.gameflow.core.entity.{Entity, Component}
import org.gameflow.utils.{Vec3, ColorUtils}
import org.gameflow.java2D.physics.Location
import scala.math

/**
 *
 */
class CubeSize(fadeintime: Double, w: Double, h: Double) extends Component {

  val baseSize = Vec3(w, h, 0)
  val size = Vec3(0, 0, 0)
  var scale = 0.0


  def update(entity: Entity, clock: Clock) {
    if (scale < 1.0) {
      val seconds: Double = clock.frameDurationSeconds
      scale = scale * (1.0 - seconds / fadeintime) + 1.0 * seconds / fadeintime
    }

    val location: Location = entity.getComponentOfType[Location]
    if (location != null) {
      size.set(baseSize)
      size.x *= math.abs(location.velocity.x) * 0.03 * scale
      size.y *= math.abs(location.velocity.y) * 0.04 * scale
    }
  }

}