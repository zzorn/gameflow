package org.gameflow.core.clock

import org.gameflow.core.{Game, Manager}


/**
 * Game clock, used to calculate frame duration, and do frame delays.
 */
trait Clock extends Manager {

  protected final val SecondsToNanoseconds: Double = 1E9
  protected final val NanosecondsToSeconds: Double = 1E-9

  private var previousTime = 0L
  private var _frameDurationNanoseconds = 0L
  private var _frameDurationSeconds = 0.0
  private var _fps = 0.0

  /**
   * Number of frames elapsed since start of the game.
   * Can also be changed directly if counting from some other number is desired.
   */
  var frameNumber = 0L

  /**
   * Total time elapsed since the start of the game, in seconds.
   * Can also be changed directly if counting from some other time is desired.
   */
  var gameTimeSeconds = 0.0

  /**
   * Can be used to adjust the speed of the in-game time, for various game effects.
   * 1.0 = normal time, 0.5 = double speed, 2.0 = double slower.
   */
  var timeScale = 1.0

  /**
   * @return duration of the previous frame in nanoseconds.
   */
  final def frameDurationNanoseconds = _frameDurationNanoseconds

  /**
   * @return duration of the previous frame in seconds.
   */
  final def frameDurationSeconds = _frameDurationSeconds

  /**
   * @return number of frames per second.  Zero if not yet started.
   */
  final def fps = _fps

  /**
   * Delay a bit and calculate the time of the frame.
   */
  final def tick() {
    // Delay using implementation dependent strategy
    doDelay(previousTime, getCurrentNanoTime)

    // Calculate frame duration
    val now = getCurrentNanoTime
    _frameDurationNanoseconds = if (previousTime == 0) 0 else now - previousTime
    previousTime = now

    // Calculate duration in seconds
    _frameDurationSeconds = _frameDurationNanoseconds * NanosecondsToSeconds

    // Do any time scaling if needed
    if (timeScale != 1.0) {
      _frameDurationNanoseconds = (_frameDurationNanoseconds * timeScale).toLong
      _frameDurationSeconds *= timeScale
    }

    // Update fps
    if (_frameDurationSeconds > 0) {
      _fps = 1.0 / _frameDurationSeconds
    }

    // Update game time
    gameTimeSeconds += _frameDurationSeconds

    // Increase frame count
    frameNumber += 1
  }

  /**
   * Delay for some time
   */
  protected def doDelay(previousTimeNs: Long, currentTimeNs: Long)

  /**
   * Just returns the current system time in nanoseconds.  Can be overridden for unit testing or the like.
   */
  def getCurrentNanoTime: Long = System.nanoTime()

  override final def update(game: Game) {
    tick()
  }
}