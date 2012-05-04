package org.gameflow.state

import org.gameflow.Game
import org.gameflow.entity.EntityGroups
import org.gameflow.entity.EntityGroupsImpl
import org.gameflow.pass.Pass
import org.gameflow.entity.BaseEntity

/**
 * 
 */
public abstract class BaseGameState(override val name: String): BaseEntity(), GameState {

    override val entityGroups : EntityGroups =  EntityGroupsImpl()

    override fun doPassOnChildren(pass : Pass, game: Game) {
        entityGroups.doPass(pass, game)
    }

}