package net.zzorn.gameflow.examples.tilemap

import net.zzorn.gameflow.GameBase
import java.awt.Graphics2D
import net.zzorn.gameflow.picture.Picture

/**
 * Example of using a tiled map.
 */
object TileMapExample extends GameBase("TileMapExample") {

  override protected def init() {

  }


  override protected def draw(screen: Graphics2D, screenW: Int, screenH: Int) {
    var wall: Picture = pictureStore.get("examples/images/wall.png")

    var y = 0
    while (y < 10) {
      var x = 0
      while (x < 10) {
        wall.draw(screen, x * 64, y * 64)
        x += 1
      }
      y += 1
    }
  }

  override protected def update(durationSec: Double) {

  }

}