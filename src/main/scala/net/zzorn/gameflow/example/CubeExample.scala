package net.zzorn.gameflow.example

import net.zzorn.gameflow.GameBase
import net.zzorn.utils.ColorUtils
import java.awt.{AlphaComposite, Graphics2D, Color}

/**
 *
 */
object CubeExample extends GameBase("Cube Example") {

  case class Cube(var x: Double = 0,
                  var y: Double = 0,
                  var w: Double = 64,
                  var h: Double = 64,
                  var bw: Double = 64,
                  var bh: Double = 64,
                  var color: Color = Color.RED,
                  var dx: Double = 0,
                  var dy: Double = 0) {

    val fadeintime = 5.0
    val airresistance = 0.5
    val gravitation = 200.0
    var scale = 0.0;

    init()

    def init() {
      scale = 0
      x = math.random * 1000 - 300
      y = math.random * 1000 - 100
      bw = math.random * 80
      bh = math.random * 100
      color = new Color(ColorUtils.HSLtoRGB(
        math.random.toFloat * 0.25f + 0.9f,
        math.random.toFloat*0.5f+0.1f,
        math.random.toFloat*0.3f+0.15f,
        math.random.toFloat * 0.3f + 0.4f), false)
      dx = (math.random - 0.5) * 400
      dy = (math.random - 0.5) * 400
      calculateSize()
    }

    def update(s: Double) {
      dx *= math.max(0, 1.0 - airresistance * s)
      dy *= math.max(0, 1.0 - airresistance * s)
      dy += gravitation * s
      x += dx * s
      y += dy * s
      if (scale < 1.0) scale = scale * (1.0 - s/fadeintime) + 1.0 * s/fadeintime
      if (y > 1000) init()
      calculateSize()
    }

    def draw(graphics: Graphics2D) {
      graphics.setColor(color)
      graphics.fillRect(x.toInt, y.toInt, w.toInt, h.toInt)
    }

    private def calculateSize() {
      w = bw * math.abs(dx) * 0.03 * scale
      h = bh * math.abs(dy) * 0.04 * scale
    }
  }

  private var cubes: List[Cube] = Nil


  override protected def setup() {
    var i = 0
    while (i < 1000) {
      cubes ::= Cube()
      i += 1
    }

  }

  override protected def update(durationSec: Double) {
    cubes foreach {c => c.update(durationSec)}
  }


  override protected def render(screen: Graphics2D) {
    cubes foreach {c => c.draw(screen)}
  }

}