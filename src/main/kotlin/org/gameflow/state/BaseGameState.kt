package org.gameflow.state

import org.gameflow.Game
import org.gameflow.component.ComponentizedBase

/**
 * 
 */
public abstract class BaseGameState(override val name: String): ComponentizedBase(), GameState {

}