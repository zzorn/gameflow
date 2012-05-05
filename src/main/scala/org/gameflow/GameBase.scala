package org.gameflow

import java2D.rendering.GameCanvas
import gameflow.picture.PictureManager
import java.awt.{Graphics2D, Color}
import org.gameflow.utils.{ParameterChecker, SimpleFrame, Logging}

/**
 * Extend this in an object, and implement update, render, and optionally init and shutdown.
 */
// TODO: Map background drawing, cache background to raster, update on change or resize, draw from background when sprites move on foreground
// TODO: Checkout double buffering tutorial http://www.cokeandcode.com/info/tut2d.html  http://content.gpwiki.org/index.php/Java:Tutorials:Double_Buffering  http://docs.oracle.com/javase/tutorial/extra/fullscreen/doublebuf.html
class GameBase(title: String = "GameFlow",
               initialTargetFps: Double = 200.0,
               defaultWidth: Int = 800,
               defaultHeight: Int = 600,
               picturePath: String = "") {

  final private val SecondsToNanoseconds: Double = 1000000000.0
  final private val NanosecondsToSeconds: Double = 1.0 / SecondsToNanoseconds
  final private val NanosecondsToMilliseconds: Double = 1.0 / 1000000.0

  final private var running = false
  final private var lastTimestamp = 0L
  final private var _frame: SimpleFrame = null
  final private var _canvas: GameCanvas = null
  final private var _currentFps: Double = 0.0
  final private var _targetFps: Double = 0.0


  final private val _pictureStore: PictureManager = new PictureManager(picturePath)

  targetFps = initialTargetFps

  /**
   * Default main method, that calls start()
   */
  def main(args: Array[String]) {
    start()
  }

  final def pictureStore: PictureManager = _pictureStore

  final def canvas: GameCanvas = _canvas

  final def frame: SimpleFrame = _frame

  final def currentFps: Double = _currentFps

  final def targetFps = _targetFps

  final def targetFps_=(targetFps: Double) {
    ParameterChecker.requirePositive(targetFps, 'targetFps)
    _targetFps = targetFps
  }

  /**
   * Starts running the main loop.
   */
  final def start() {
    if (!running) {
      running = true

      // Setup logging
      Logging.initializeLogging(getClass.getPackage.getName, "net.gameflow")

      createScreen()

      init()

      while (running) {
        var duration = tickClock()

        update(duration)

        var surface: Graphics2D = canvas.acceleratedSurface
        //surface.setColor(Color.black);
        //surface.fillRect(0,0,800,600);

        draw(surface, canvas.getWidth, canvas.getHeight)

        // We'll need to manually dispose the graphics
        surface.dispose()

        // Flip the page, and show rendered graphics
        canvas.flipPage()
      }

      shutdown()
    }
  }

  /**
   * Stops the game after the next run through the main loop finishes.
   */
  final def stop() {
    running = false
  }

  /**
   * Called before the main loop starts, after the screen has been created.
   */
  protected def init() {}

  /**
   * Called once in each mainloop, immediately before render.
   * @param durationSec the duration of the previous frame, in seconds.
   */
  protected def update(durationSec: Double) {}

  /**
   * Render the game contents to the specified screen raster.
   * @param screen the graphics to render to.
   */
  protected def draw(screen: Graphics2D, screenW: Int, screenH: Int) {}

  /**
   * Called after the mainloop has terminated, and before the program exits.
   * The screen is still available and visible.
   * Can be used to do any needed cleanup.
   */
  protected def shutdown() {}


  final private def createScreen() {
    _canvas = new GameCanvas()
    _frame = new SimpleFrame(title, _canvas, defaultWidth, defaultHeight)
    _canvas.setup()
  }


  final private def tickClock(): Double = {

    // Sleep any extra required time
    if (lastTimestamp != 0) {
      val targetFrameDurationNs = (SecondsToNanoseconds / targetFps).toLong
      val actualFrameDurationNs = lastTimestamp - System.nanoTime()
      val requiredDelayMs = math.max(1, (targetFrameDurationNs - actualFrameDurationNs) * NanosecondsToMilliseconds)
      //Thread.sleep(requiredDelayMs.toLong)
      Thread.sleep(1)
    }

    // Get time since last frame
    val now = System.nanoTime()
    val duration = if (lastTimestamp == 0) 0.0 else (now - lastTimestamp) * NanosecondsToSeconds
    lastTimestamp = now

    // Update fps
    if (duration > 0) {
      _currentFps = 1.0 / duration
    }

    // Return time since last tick (or zero if first tick)
    duration
  }


}