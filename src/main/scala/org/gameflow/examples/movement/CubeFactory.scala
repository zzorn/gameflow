package org.gameflow.examples.movement

import java.awt.Color
import org.gameflow.utils.{Vec3, ColorUtils}
import org.gameflow.core.entity.{Entity, EntityFactoryBase}
import org.gameflow.java2D.physics.SimpleMovement

/**
 * Creates animated cube entities.
 */
class CubeFactory(gravity: Vec3, fadeintime: Double, airresistance: Double) extends EntityFactoryBase {


  protected def populateEntity(entity: Entity) {

    val movement: SimpleMovement = new SimpleMovement() {
      override def update(durationSeconds: Double) {
        velocity *= math.max(0, 1.0 - airresistance * durationSeconds)
        super.update(durationSeconds)
      }
    }

    val appearance: CubeAppearance = new CubeAppearance()

    appearance.color = new Color(ColorUtils.HSLtoRGB(
      math.random.toFloat * 0.25f + 0.9f,
      math.random.toFloat * 0.5f + 0.1f,
      math.random.toFloat * 0.3f + 0.15f,
      math.random.toFloat * 0.3f + 0.4f), false)

    movement.pos.x = math.random * 1000 - 300
    movement.pos.y = math.random * 1000 - 300
    movement.velocity.x = (math.random - 0.5) * 400
    movement.velocity.y = (math.random - 0.5) * 400
    movement.thrust.set(gravity)

    entity.addComponent('Location, movement)
    entity.addComponent('Appearance2D, appearance)
    entity.addComponent('CubeSize, new CubeSize(fadeintime, math.random * 80, math.random * 80))

  }


}
