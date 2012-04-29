package org.gameflow

/**
 * 
 */
public trait Clock {

    fun tick(): Double

    val fps: Double

}