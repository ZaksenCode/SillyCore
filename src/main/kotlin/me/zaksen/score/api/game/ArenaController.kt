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

    /**
     * Moves players into a suitable arena that can accommodate them all.
     * @return true - if the player is connected, false - if suitable arena not found.
     */
    open fun joinRandomArena(players: Set<Player>): Boolean {
        val suitableArena = getArenaFor(players.size) ?: return false

        players.forEach {
            suitableArena.joinPlayer(it)
        }

        return true
    }

    /**
     * A method for obtaining an arena that can hold the specified number of players and is accessible.
     * @return suitable arena
     */
    open fun getArenaFor(count: Int = 1): T? {
        for(arena in arenas) {
            if(arena.canPlace(count) && arena.isAvailable()) {
                return arena
            }
        }

        return null
    }
}