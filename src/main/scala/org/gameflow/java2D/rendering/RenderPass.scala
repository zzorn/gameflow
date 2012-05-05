package org.gameflow.java2D.rendering

import org.gameflow.core.pass.Pass
import org.gameflow.core.Game
import org.gameflow.core.entity.Entity
import java.awt.{Graphics2D, Color}
import org.gameflow.utils.{Vec3, SimpleFrame}
import org.gameflow.java2D.camera.{StationaryCamera, Camera}
import org.gameflow.java2D.physics.Location

/**
 * A render pass for java 2D renderable entities.
 */
class RenderPass(windowTitle: String = "",
                 width: Int = 800,
                 height: Int = 600,
                 var camera: Camera = new StationaryCamera(),
                 clearToColor: Color = Color.BLACK) extends Pass {

  private var canvas: GameCanvas = null
  private var frame: SimpleFrame = null
  private var surface: Graphics2D = null
  private var screenPos = Vec3()

  override protected def onInit(game: Game) {
    // Create canvas and the frame
    canvas = new GameCanvas()
    frame = new SimpleFrame(windowTitle, canvas, width, height)
    canvas.setup()
    if (camera != null) camera.setScreenSize(width, height)

    // TODO: Option to allow screen resize, listen to resize, update camera screen size
    // TODO: Option to not create frame, just canvas
  }

  override def startPass(game: Game) {
    // Update camera if we have one
    if (camera != null) camera.update(game.clock)

    // Get surface to draw on
    surface = canvas.acceleratedSurface

    // Clear the canvas if a background color is specified
    if (clearToColor != null) {
      surface.setColor(clearToColor)
      surface.fillRect(0, 0, canvas.getWidth, canvas.getHeight)
    }

  }

  def handleEntity(entity: Entity, game: Game) {
    val location: Location = entity.getComponentOfType[Location]
    val appearance: Appearance2D = entity.getComponentOfType[Appearance2D]
    if (location != null && appearance != null) {
      // Project location
      if (camera != null) camera.worldPosToScreenPos(location.pos, screenPos)
      else screenPos.set(location.pos)

      // Render entity
      appearance.render(entity, surface, screenPos.x, screenPos.y, canvas.getWidth, canvas.getHeight)
    }
  }

  override def endPass(game: Game) {
    // We'll need to manually dispose the graphics
    surface.dispose()
    surface = null

    // Flip the page, and show rendered graphics
    canvas.flipPage()
  }


  override protected def onDeInit(game: Game) {
    frame.setVisible(false)
  }
}