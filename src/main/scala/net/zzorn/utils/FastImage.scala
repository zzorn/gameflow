package net.zzorn.utils

import gfx.Raster
import java.awt.image.{BufferedImage, DirectColorModel, MemoryImageSource}
import java.awt.{Color, Graphics, Toolkit, Image}

/**
 * Fast, low level access image container.
 */
class FastImage(val width: Int, val height: Int) {
  require(width > 0, "Width should be positive")
  require(height > 0, "Height should be positive")

  private var imageSource: MemoryImageSource = null
  private var _image: Image = null
  private var _raster: Raster = null


  initialize()

  private def initialize() {

    // Don't include alpha for normal on screen rendering, as it takes longer due to masking
    //val rgbColorModel: DirectColorModel = new DirectColorModel(32, 0xff0000, 0x00ff00, 0x0000ff, 0xff000000)
    val rgbColorModel: DirectColorModel = new DirectColorModel(24, 0xff0000, 0x00ff00, 0x0000ff)

    _raster = Raster(width, height)
    imageSource = new MemoryImageSource(width, height, rgbColorModel, _raster.data, 0, width)
    imageSource.setAnimated(true)

    _image = Toolkit.getDefaultToolkit().createImage(imageSource)

    raster.clear()
  }

  def raster: Raster = _raster

  def image: Image = _image

  def renderToGraphics(context: Graphics) {
    context.drawImage(_image, 0, 0, null)
  }

  def renderToGraphics(context: Graphics, area: Area) {
    renderToGraphics(context,
                     area.getMinX, area.getMinY,
                     area.getMaxX, area.getMaxY)
  }

  def renderToGraphics(context: Graphics, x1: Int, y1: Int, x2: Int, y2: Int) {
    context.drawImage(_image,
                      x1, y1, x2, y2,
                      x1, y1, x2, y2,
                      null)
  }

  def createBufferedImage: BufferedImage = {
    val buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    /*
            val transparentRgbColorModel: DirectColorModel = new DirectColorModel(32, 0xff0000, 0x00ff00, 0x0000ff, 0xff000000)
            val transparentImageSource = new MemoryImageSource(width, height, transparentRgbColorModel, imageData, 0, width)
            val transparentImage = Toolkit.getDefaultToolkit().createImage(imageSource)
    */

    //        buf.getGraphics.drawImage(transparentImage, 0, 0, null)

    buf.getGraphics.drawImage(_image, 0, 0, null)

    buf
  }


}