package net.zzorn.ld23warmup

import net.zzorn.utils.{Area, FastImagePanel, SimpleFrame}
import net.zzorn.utils.gfx.Raster
import java.awt.Color


/**
 *
 */
object PyramidWarmup extends GameBase {

  private var posX = 0.0
  private var posY = 0.0
  private var velX = 0.0
  private var velY = 0.0

  override protected def setup() {
    velX = 25.0
  }

  override protected def update(durationSec: Double) {
    velY += 9.0 *  durationSec

    posX += velX * durationSec
    posY += velY * durationSec

    if (posY > screen.height) {
      posX = 0.0
      posY = 0.0
      velX = 5.0
      velY = 0.0
    }
  }

  override protected def render(screen: Raster) {
    screen.clear()
    screen.setPixel(posX.toInt, posY.toInt, Color.WHITE.getRGB)
  }

  override protected def shutdown() {

  }
}

