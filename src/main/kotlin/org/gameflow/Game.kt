package org.gameflow

import java.util.List
import java.util.Map
import java.util.HashMap
import org.gameflow.state.GameState
import org.gameflow.util.ListenableCollection
import java.util.concurrent.ConcurrentHashMap
import org.gameflow.state.StateManager
import org.gameflow.state.StateManagerImpl
import org.gameflow.entity.EntityGroup
import org.gameflow.entity.EntityGroupsImpl
import org.gameflow.entity.EntityGroups
import java.util.ArrayList
import org.gameflow.pass.Pass
import java.util.Collection
import org.gameflow.pass.Passable
import java.util.Collections
import org.gameflow.entity.BaseEntity
import org.gameflow.clock.ClockImpl
import java.util.concurrent.ConcurrentSkipListSet

/**
 * 
 */
public open class Game(
        public val clock: Clock = ClockImpl(),
        public val stateManager: StateManager = StateManagerImpl(),
        public val groups : EntityGroups = EntityGroupsImpl()
    ):
        BaseEntity(),
        Clock by clock,
        EntityGroups by groups,
        StateManager by stateManager  {

    private val passes : Collection<Pass> = ConcurrentSkipListSet<Pass>()


    public open fun main(args: Array<String>) {
        start()
    }

    public fun addPass(pass: Pass) {
        if (!passes.contains(pass)) {
            passes.add(pass)
            if (started) pass.init(this)
        }
    }

    public fun removePass(pass: Pass) {
        if (passes.contains(pass)) {
            passes.remove(pass)
            if (started) pass.deInit(this)
        }
    }

    public fun hasPass(pass: Pass): Boolean = passes.contains(pass)
    public fun getPasses(): Collection<Pass> = Collections.unmodifiableCollection(passes) !!

    private var started = false
    private var stopped = false

    /** Calls init, then starts the game loop.  Call stop to exit it. */
    public final fun start() {
        if (!started) {
            started = true

            for (pass in passes) { pass.init(this) }
            init()

            while(!stopped) {
                doLoop()
            }

            deInit()
            for (pass in passes) { pass.deInit(this) }
        }
    }

    /** Causes the game to stop after the current run through the main loop finishes */
    public final fun stop() {
        stopped = true
    }

    /** Runs through the game loop once. */
    public final fun doLoop() {
        // Update time
        clock.tick()

        // Change game state if needed
        stateManager.doGameStateUpdate(this)

        // Run passes in order
        for (pass in passes) {
            pass.startPass(this)

            // Apply pass to game and all sub-components
            doPass(pass, this)

            pass.endPass(this)
        }
    }

    override fun doPassOnChildren(pass : Pass, game: Game) {
        groups.doPass(pass, game)
        stateManager.doPass(pass, game)
    }

    /** Called when the game loop is started. */
    public open fun init() {}

    /** Called after the game loop is stopped. */
    public open fun deInit() {}

}