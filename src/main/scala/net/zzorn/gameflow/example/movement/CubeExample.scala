package net.zzorn.gameflow.example.movement

import scala.math
import java.awt.{Graphics2D, Color}

import net.zzorn.gameflow.GameBase
import net.zzorn.gameflow.entity.{Entity3D, Entity2D}
import net.zzorn.gameflow.gamemap.SimpleMap
import net.zzorn.utils.{Vec2, Vec3, ColorUtils}

/**
 * Example of drawing and moving entity classes.
 */
object CubeExample extends GameBase("Cube Example") {

  val gravity = Vec2(0, 200)
  val fadeintime = 5.0
  val airresistance = 0.5

  var secondsSoFar = 0.0

  private val gameMap = new SimpleMap()

  override protected def init() {
    var i = 0
    while (i < 1000) {
      gameMap.add(new Cube(gravity, fadeintime, airresistance))
      i += 1
    }
  }

  override protected def update(durationSec: Double) {
    gameMap.update(durationSec)

    // Move the camera around a bit
    secondsSoFar += durationSec
    gameMap.camera.cameraPos.x = math.sin(secondsSoFar / 2) * 600
  }


  override protected def draw(screen: Graphics2D, screenW: Int, screenH: Int) {
    gameMap.draw(screen, screenW, screenH)
  }

}