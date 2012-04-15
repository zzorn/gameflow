package net.zzorn.utils.gfx

import java.awt.Color
import net.zzorn.utils.ParameterChecker._
import net.zzorn.utils.Area

object Raster {

  def apply(width: Int, height: Int): Raster = {
    Raster(new Array[Int](width * height), width, height)
  }
}

/**
 *
 */
case class Raster(data: Array[Int], width: Int, height: Int) {

  private val updatedArea: Area = new Area()
  private val area: Area = new Area(0, 0, width, height)

  /**
   * Clears the whole raster to black.
   */
  def clear() {
    clearToColor(Color.BLACK.getRGB)
  }

  /**
   * Clears the whole raster to the specified color.
   */
  def clearToColor(color: Int) {
    var i = 0
    while (i < data.length) {
      data(i) = color
      i += 1
    }
    updatedArea.includeArea(area)
  }

  /**
   * Sets a pixel to the specified color.  If the pixel is outside the raster bounds, it is not rendered.
   * Also updates the updated area to contain the pixel.
   */
  @inline final def setPixel(x: Int, y: Int, color: Int) {
    if (x >= 0 && x < width &&
        y >= 0 && y < height) {
      data(x + y*width) = color
      updatedArea.includePoint(x, y)
    }
  }

  /**
   * Sets a pixel to the specified color.  Does no error checking, apart from built-in java array bounds checking.
   * Does not update the updatedArea (that is left for the caller to do).
   */
  @inline final def setPixelRaw(x: Int, y: Int, color: Int) {
    data(x + y*width) = color
  }

  /**
   * Sets a pixel at the specified data array index to the specified color.
   * Does no error checking, apart from built-in java array bounds checking.
   * Does not update the updatedArea (that is left for the caller to do).
   */
  @inline final def setPixelRaw(index: Int, color: Int) {
    data(index) = color
  }


  def getArea: Area = new Area(area)

  def getUpdatedArea: Area = updatedArea

  def clearUpdatedArea() {
    updatedArea.clear()
  }

  def markDirty() {
    updatedArea.setArea(area)
  }

}



