package net.zzorn.gameflow.gamemap

import net.zzorn.utils.Vec3

/**
 * A camera that just focuses on a specified point.
 */
class StationaryCamera(val cameraPos: Vec3 = Vec3()) extends Camera {

  def update(seconds: Double) {
  }

}