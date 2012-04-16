package net.zzorn.ld23warmup

import net.zzorn.utils.gfx.Raster
import java.awt.Color
import net.zzorn.utils.{ColorUtils, Area, FastImagePanel, SimpleFrame}


/**
 *
 */
object PyramidWarmup extends GameBase {

  private var posX = 0.0
  private var posY = 0.0
  private var velX = 0.0
  private var velY = 0.0

  private val pic: Raster = Raster(64, 64)

  def hueFilter(alphaFactor: Float = 0f)(raster: Raster, x: Int, y: Int, color: Int): Int = {
    val rx = 1.0f * x / raster.width
    val ry = 1.0f * y / raster.height
    val (r, g, b) = ColorUtils.HSLtoRGB(rx, ry, 0.5f)
    ColorUtils.createRGBAColor(r, g, b, 1.0f - rx * alphaFactor)
  }

  override protected def setup() {
    velX = 45.0

    pic.fill(Color.ORANGE.getRGB)
    pic.filter(hueFilter(1f) _ )
  }

  override protected def update(durationSec: Double) {
    velY += 19.0 *  durationSec

    posX += velX * durationSec
    posY += velY * durationSec

    if (posY > screen.height) {
      posX = -20.0
      posY = -30.0
      velX = 25.0
      velY = 5.0
    }
  }

  override protected def render(screen: Raster) {
    //screen.clear()
    screen.filter(hueFilter() _ )
    screen.drawPixel(posX.toInt, posY.toInt, Color.WHITE.getRGB)
    screen.drawRect(posX.toInt, posY.toInt, 1000, 2000, ColorUtils.createRGBAColor(0.8f, 0.6f, 0.1f, 0.4f), true)
    screen.drawRaster(pic, posX.toInt, posY.toInt, true)
  }

  override protected def shutdown() {

  }
}

