package org.gameflow.entity

import java.util.HashMap
import org.gameflow.pass.Pass
import org.gameflow.Game

/**
 * 
 */
public class EntityGroupsImpl: EntityGroups {
    private val entityGroups = HashMap<String, EntityGroup>()

    public override fun groups() = entityGroups.values()

    public override fun createGroup(name: String) : EntityGroup {
        if (entityGroups.containsKey(name)) throw Error("An EnityGroup named '$name' already exists!")
        val group = EntityGroup(name)
        entityGroups.put(name, group)
        return group
    }

    public override fun getGroup(name: String) : EntityGroup {
        val group = entityGroups.get(name)
        if (group == null) throw Error("No EntityGroup named '$name' found!")
        else return group
    }

    public override fun containsGroup(name: String): Boolean = entityGroups.containsKey(name)


    override fun doPassOnChildren(pass : Pass, game : Game) {
        for (group in entityGroups.values()) {
            group.doPass(pass, game)
        }
    }
}