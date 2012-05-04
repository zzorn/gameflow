package org.gameflow

/**
 * 
 */
public trait Clock {

    fun tick(): Double

    fun frameDurationSeconds(): Double
    fun frameDurationMilliseconds(): Long
    fun frameNumber(): Long
    fun gameTime(): Double

    fun fps(): Double

}