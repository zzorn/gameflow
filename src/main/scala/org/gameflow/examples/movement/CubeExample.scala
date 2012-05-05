package org.gameflow.examples.movement

import org.gameflow.core.Game
import org.gameflow.java2D.rendering.RenderPass
import org.gameflow.java2D.camera.WobbleCamera
import org.gameflow.core.pass.Pass
import org.gameflow.core.entity.Entity
import org.gameflow.utils.Vec3
import org.gameflow.java2D.physics.{Location, PhysicsPass}
import org.gameflow.java2D.Simple2DGame

/**
 * Example of drawing and moving entity classes.
 */
object CubeExample extends Simple2DGame {

  val gravity = Vec3(0, 200, 0)
  val fadeintime = 5.0
  val airresistance = 0.5

  val cubeFactory = new CubeFactory(gravity, fadeintime, airresistance)

  override protected def onInit() {
    val cubeGroup = groups.createGroup('cubes)

    passes.addPass(new PhysicsPass)
    passes.addPass(new Pass {
      def handleEntity(entity: Entity, game: Game) {
        entity.withComponentOfType[CubeSize] {_.update(entity, clock)}

        entity.withComponentOfType[Location] {location =>
          if (location.pos.y > 600) {
            // Kill cube, spawn new one
            cubeGroup.remove(entity)
            cubeGroup.add(cubeFactory.createEntity())
          }
        }
      }
    })
    passes.addPass(new RenderPass("Cube Example", camera = new WobbleCamera(wobbleSize = Vec3(100, 10), wobbleSpeed = 2.5)))

    // Add cubes
    var i = 0
    while (i < 1000) {
      cubeGroup.add(cubeFactory.createEntity())
      i += 1
    }
  }

}