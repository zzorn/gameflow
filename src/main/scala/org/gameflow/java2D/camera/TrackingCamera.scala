package org.gameflow.java2D.camera

import org.gameflow.core.entity.Entity
import org.gameflow.utils.Vec3
import org.gameflow.java2D.physics.Location
import org.gameflow.core.clock.Clock


/**
 * A camera that tracks a specified entity, focusing a bit ahead of it.
 */
class TrackingCamera(initialTrackedEntity: Entity,
                     var leadingAmount: Double = 10,
                     var trackingSpeed: Double = 10) extends Camera {

  private var _trackedEntity: Entity = null
  private val targetPos = Vec3()
  private val entityPos = Vec3()
  private val oldEntityPos = Vec3()

  setTrackedEntity(initialTrackedEntity)

  def trackedEntity: Entity = _trackedEntity

  def setTrackedEntity(trackedEntity: Entity) {
    _trackedEntity = trackedEntity
    if (_trackedEntity != null) {
      val location: Location = _trackedEntity.getComponentOfType[Location]
      if (location != null) {
        oldEntityPos.set(location.pos)
        entityPos.set(location.pos)
        targetPos.set(location.pos)
        cameraPos.set(location.pos)
      }
    }
    else{
      cameraPos.set(0,0,0)
    }
  }

  override def update(clock: Clock) {
    if (_trackedEntity != null) {
      val location = _trackedEntity.getComponentOfType[Location]
      if (location != null) {
        oldEntityPos.set(entityPos)
        entityPos.set( location.pos )

        // Calculate target
        targetPos.set(entityPos)
        targetPos +*=(location.velocity, leadingAmount)

        // Move towards target
        cameraPos.mixWith(targetPos, math.min(1.0, clock.frameDurationSeconds * trackingSpeed))
      }
    }
  }

}
