package org.gameflow.java2D

import org.gameflow.core.GameBase
import rendering.RenderPass
import org.gameflow.core.clock.{FixedDelayClock, Clock}

/**
 * Base for a 2D game.  Provides picture loading manager, rendering pass, input manager, etc.
 */
class Simple2DGame extends GameBase {

  val renderPass:     RenderPass     = new RenderPass()
  val pictureManager: PictureManager = new PictureManager()

  override protected def setup() {
    super.setup()

    addManager(pictureManager)

    // TODO: Input manager?
    // TODO: Add input pass
    // TODO: Music
    // TODO: Audio
    // TODO: AI Update pass
    // TODO: Scheduler

    // TODO: Should we add a simple update method to entities too?

    passes.addPass(renderPass)
  }

}