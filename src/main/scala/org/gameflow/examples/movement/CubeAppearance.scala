package org.gameflow.examples.movement

import org.gameflow.java2D.rendering.Appearance2D
import org.gameflow.core.entity.Entity
import java.awt.{Color, Graphics2D}

/**
 *
 */
class CubeAppearance extends Appearance2D {

  var color: Color = Color.WHITE

  def render(entity: Entity, g: Graphics2D, x: Double, y: Double, screenWidth: Int, screenHeight: Int) {
    val size: CubeSize = entity.getComponentOfType[CubeSize]
    val w = size.size.x.toInt
    val h = size.size.y.toInt

    g.setColor(color)
    g.fillRect(x.toInt - w/2, y.toInt - h/2, w, h)
  }
}