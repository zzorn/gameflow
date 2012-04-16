package net.zzorn.utils

import gfx.Raster
import javax.swing.JPanel
import java.awt.Graphics
import java.awt.event.{ComponentAdapter, ComponentEvent}

/**
 *
 */
class FastImagePanel extends JPanel {

  private var _fastImage: FastImage = null

  addComponentListener(new ComponentAdapter {

    override def componentShown(e: ComponentEvent) {
      if (_fastImage == null) buildFastImage()
    }

    override def componentResized(e: ComponentEvent) {
      buildFastImage()
    }
  })

  def fastImage: FastImage = _fastImage
  def raster: Raster = _fastImage.raster

  override def paintComponent(g: Graphics) {
    _fastImage.renderToGraphics(g, _fastImage.raster.getUpdatedArea)
    _fastImage.raster.clearUpdatedArea()
  }


  def buildFastImage() {
    _fastImage = new FastImage(getWidth, getHeight)
    _fastImage.raster.markDirty()
  }


}
