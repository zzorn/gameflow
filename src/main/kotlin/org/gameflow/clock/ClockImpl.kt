package org.gameflow.clock

import org.gameflow.Clock

/**
 * 
 */
public class ClockImpl: Clock {


    override fun tick() : Double {
        throw UnsupportedOperationException()
    }
    override fun frameDurationSeconds() : Double {
        throw UnsupportedOperationException()
    }
    override fun frameDurationMilliseconds() : Long {
        throw UnsupportedOperationException()
    }
    override fun frameNumber() : Long {
        throw UnsupportedOperationException()
    }
    override fun gameTime() : Double {
        throw UnsupportedOperationException()
    }
    override fun fps() : Double {
        throw UnsupportedOperationException()
    }
}