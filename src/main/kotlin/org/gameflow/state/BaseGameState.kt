package org.gameflow.state

import org.gameflow.Game
import org.gameflow.component.ComponentizedBase
import org.gameflow.entity.EntityGroups
import org.gameflow.entity.EntityGroupsImpl

/**
 * 
 */
public abstract class BaseGameState(override val name: String): ComponentizedBase(), GameState {

    override val entityGroups : EntityGroups =  EntityGroupsImpl()
}