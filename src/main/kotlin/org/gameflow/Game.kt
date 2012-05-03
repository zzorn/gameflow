package org.gameflow

import java.util.List
import java.util.Map
import java.util.HashMap
import org.gameflow.state.GameState
import org.gameflow.util.ListenableCollection
import java.util.concurrent.ConcurrentHashMap
import org.gameflow.state.StateManager
import org.gameflow.state.StateManagerImpl
import org.gameflow.component.ComponentizedBase
import org.gameflow.entity.EntityGroup
import org.gameflow.entity.EntityGroupsImpl
import org.gameflow.entity.EntityGroups
import java.util.ArrayList
import org.gameflow.pass.Pass
import java.util.Collection
import org.gameflow.pass.Passable

/**
 * 
 */
public open class Game(
        public val stateManager: StateManager = StateManagerImpl(),
        public val groups : EntityGroups = EntityGroupsImpl()
    ):
        ComponentizedBase(),
        StateManager by stateManager  {

    public val entityPasses: List<Pass> = ArrayList<Pass>()

    private var stopped = false

    override fun containedPassables() : Collection<Passable> {
        return groups.containedPassables()
    }



    /** Calls init, then starts the game loop.  Call stop to exit it. */
    public final fun start() {
        while(!stopped) {
            doLoop()
        }
    }

    public final fun stop() {
        stopped = true
    }

    /** Runs through the game loop once. */
    public final fun doLoop() {
        // Change game state if needed
        stateManager.doGameStateUpdate(this)

        // Run passes in order
        for (pass in entityPasses) {
            // Apply pass to each entity group
        }
    }

}