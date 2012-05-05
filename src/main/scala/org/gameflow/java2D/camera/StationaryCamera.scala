package org.gameflow.java2D.camera

import org.gameflow.utils.Vec3

/**
 * A camera that just focuses on a specified point.
 */
class StationaryCamera(val initialCameraPos: Vec3 = Vec3()) extends Camera {

  cameraPos.set(initialCameraPos)

}