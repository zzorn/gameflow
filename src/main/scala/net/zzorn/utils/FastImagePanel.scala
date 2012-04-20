package net.zzorn.utils

import net.zzorn.utils.Raster
import gfx.Raster
import javax.swing.JPanel
import java.awt.Graphics
import java.awt.event.{ComponentAdapter, ComponentEvent}

/**
 *
 */
class FastImagePanel extends JPanel {

  private var _fastImage: RawImage = null

  addComponentListener(new ComponentAdapter {

    override def componentShown(e: ComponentEvent) {
      if (_fastImage == null) buildFastImage()
    }

    override def componentResized(e: ComponentEvent) {
      buildFastImage()
    }
  })

  def fastImage: RawImage = _fastImage
  def raster: Raster = _fastImage.raster

  override def paintComponent(g: Graphics) {
    _fastImage.renderToGraphics(g, _fastImage.raster.getUpdatedArea)
    _fastImage.raster.clearUpdatedArea()
  }


  def buildFastImage() {
    _fastImage = new RawImage(getWidth, getHeight)
    _fastImage.raster.markDirty()
  }


}
