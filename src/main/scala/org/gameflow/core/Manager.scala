package org.gameflow.core

import pass.Passable

/**
 * Handles some game subsystem.
 */
trait Manager extends LifeCycled with Passable {

  def update(game: Game) {}

}