package org.gameflow.core.clock

/**
 * A clock that sleeps for a fixed amount of time each tick.
 * Generally seems to result in a smoother animation than a fixed framerate clock.
 */
class FixedDelayClock(initialFrameSleepMilliseconds: Long = 5) extends Clock {

  var frameSleepMilliseconds = initialFrameSleepMilliseconds

  protected def doDelay(previousTimeNs: Long, currentTimeNs: Long) {
    // Sleep the required time
    Thread.sleep(frameSleepMilliseconds)
  }

}