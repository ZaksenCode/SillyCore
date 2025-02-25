package me.zaksen.score.api.game

import org.bukkit.entity.Player

abstract class ArenaController<T: Arena> {
    protected open val arenas = mutableListOf<T>()

    /**
     * @return An empty arena ready for adding players.
     */
    open fun createArena(): T {
        val arena = createEmptyArena()
        addArena(arena)
        return arena
    }

    /**
     * Adds the specified arena to the controller internal storage
     * @param arena Arena to be added
     */
    open fun addArena(arena: T) {
        arenas.add(arena)
    }

    /**
     * @return An empty arena ready for adding players.
     */
    abstract fun createEmptyArena(): T

    /**
     * @return The arena the player is in, if there isn't one it will return null.
     */
    open fun getPlayerArena(player: Player): T? {
        for(arena in arenas) {
            if(arena.hasPlayer(player)) {
                return arena
            }
        }

        return null
    }
}