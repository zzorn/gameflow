package org.gameflow.core

import clock.{FixedDelayClock, Clock}
import entity.{EntityGroups, EntityGroupsImpl}
import org.gameflow.utils.ParameterChecker
import pass.PassManager
import state.GameStateManager

/**
 *
 */
class GameBase extends Game {

  val clock:  Clock             = new FixedDelayClock()
  val passes: PassManager       = new PassManager()
  val states: GameStateManager  = new GameStateManager()
  val groups: EntityGroups      = new EntityGroupsImpl()

  override protected def setup() {
    addManager(clock)
    addManager(passes)
    addManager(states)
    addManager(groups)
  }
}