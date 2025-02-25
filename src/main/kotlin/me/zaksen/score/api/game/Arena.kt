package me.zaksen.score.api.game

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

abstract class Arena(
    protected open val maxPlayers: Int
) {
    protected open val players = mutableSetOf<UUID>()

    /**
     * The method to call to add a player to the arena.
     *
     * @param player Player to be added
     * @param force If true, the maximum number of players will be ignored. The player will be added in any case.
     *
     * @return true - if the player is connected, false - if the player cannot connect (arena is full).
     */
    open fun joinPlayer(player: Player, force: Boolean = false): Boolean {
        if(players.size >= maxPlayers && !force) {
            return false
        }

        onJoin(player)
        players.add(player.uniqueId)
        return true
    }

    abstract fun onJoin(player: Player)

    /**
     * The method to call to remove a player from the arena.
     *
     * @param player Player to be removed
     */
    open fun leavePlayer(player: Player) {
        onLeave(player)
        players.remove(player.uniqueId)
    }

    abstract fun onLeave(player: Player)

    /**
     * @return true - if the player is in the arena, false - otherwise
     */
    fun hasPlayer(player: Player): Boolean {
        return hasPlayer(player.uniqueId)
    }

    /**
     * @return true - if the player is in the arena, false - otherwise
     */
    open fun hasPlayer(uuid: UUID): Boolean {
        return players.contains(uuid)
    }

    /**
     * @return A set of players who are in the arena
     */
    open fun getPlayers(): Set<Player> {
        val result = mutableSetOf<Player>()

        players.forEach {
            val player = Bukkit.getPlayer(it)

            if(player != null) {
                result.add(player)
            }
        }

        return result
    }
}