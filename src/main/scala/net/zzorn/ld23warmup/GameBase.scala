package net.zzorn.ld23warmup

import net.zzorn.utils.gfx.Raster
import net.zzorn.utils.{ParameterChecker, Area, FastImagePanel, SimpleFrame}

/**
 * Extend this in an object, and implement update, render, and optionally setup and shutdown.
 */
class GameBase(initialTargetFps: Double = 30.0) {

  private val SecondsToNanoseconds: Double = 1000000000.0
  private val NanosecondsToSeconds: Double = 1.0 / SecondsToNanoseconds
  private val NanosecondsToMilliseconds: Double = 1.0 / 1000000.0

  private var running = false
  private var lastTimestamp = 0L
  private var _frame: SimpleFrame = null
  private var _screenPanel: FastImagePanel = null
  private var _currentFps: Double = 0.0
  private var _targetFps: Double = 0.0

  targetFps = initialTargetFps

  /**
   * Default main method, that calls start()
   */
  def main(args: Array[String]) {
    start()
  }

  def screen: Raster = _screenPanel.raster
  def screenPanel: FastImagePanel = _screenPanel
  def frame: SimpleFrame = _frame
  def currentFps: Double = _currentFps
  def targetFps = _targetFps
  def targetFps_=(targetFps: Double) {
    ParameterChecker.requirePositive(targetFps, 'targetFps)
    _targetFps = targetFps
  }

  /**
   * Starts running the main loop.
   */
  final def start() {
    if (!running) {
      running = true

      createScreen()

      setup()

      while (running) {
        var duration = tickClock()

        update(duration)

        render(screen)

        // Force a repaint that copies the rendered screen raster to the visible screen
        _screenPanel.repaint()
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
  protected def setup() {}

  /**
   * Called once in each mainloop, immediately before render.
   * @param durationSec the duration of the previous frame, in seconds.
   */
  protected def update(durationSec: Double) {}

  /**
   * Render the game contents to the specified screen raster.
   * @param screen the screen to render to.  May change if the view is resized.
   */
  protected def render(screen: Raster) {}

  /**
   * Called after the mainloop has terminated, and before the program exits.
   * The screen is still available and visible.
   * Can be used to do any needed cleanup.
   */
  protected def shutdown() {}


  private def createScreen() {
    _screenPanel = new FastImagePanel()
    _frame = new SimpleFrame("LD 23 Warmup - Pyramid Escape", _screenPanel)
    _screenPanel.buildFastImage()
  }


  private def tickClock(): Double = {

    // Sleep any extra required time
    if (lastTimestamp != 0) {
      val targetFrameDurationNs = (SecondsToNanoseconds / targetFps).toLong
      val actualFrameDurationNs = lastTimestamp - System.nanoTime()
      val requiredDelayMs = (targetFrameDurationNs - actualFrameDurationNs) * NanosecondsToMilliseconds
      Thread.sleep(requiredDelayMs.toLong)
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