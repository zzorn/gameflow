package org.gameflow.java2D.rendering

import java.awt.Graphics2D
import org.gameflow.core.entity.{Entity, Component}

/**
 *
 */
trait Appearance2D extends Component {

  def render(entity: Entity, g: Graphics2D, x: Double, y: Double, screenWidth: Int, screenHeight: Int)

}