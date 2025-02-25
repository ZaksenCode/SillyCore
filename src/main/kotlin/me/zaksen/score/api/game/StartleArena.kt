package me.zaksen.score.api.game

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import java.util.Timer

abstract class StartleArena(
    private var startingTime: Int,
    protected open val minimalPlayers: Int,
    maxPlayers: Int
): Arena(
    maxPlayers
) {
    private val defaultStartingTime = startingTime

    protected open val timerMessages = mapOf<Int, Component>(
        Pair(20, Component.translatable("text.starting.1_second")),
        Pair(40, Component.translatable("text.starting.2_second")),
        Pair(60, Component.translatable("text.starting.3_second")),
        Pair(80, Component.translatable("text.starting.4_second")),
        Pair(100, Component.translatable("text.starting.5_second")),
        Pair(200, Component.translatable("text.starting.10_second"))
    )

    protected open val timerStopMessage = Component.translatable("text.starting.abort")

    protected open var startingTask: Timer? = null

    protected open var isStarting: Boolean = false
    protected open var isStarted: Boolean = false

    override fun joinPlayer(player: Player, force: Boolean): Boolean {
        val result = super.joinPlayer(player, force)

        if(result) {
            if(players.size >= minimalPlayers) {
                if(!isStarting && !isStarted) {
                    isStarting = true
                    launchStart()
                }
            }
        }

        return result
    }

    override fun leavePlayer(player: Player) {
        super.leavePlayer(player)
        if(players.size < minimalPlayers) {
            startingTask?.cancel()
            startingTime = defaultStartingTime
        }
        getPlayers().forEach {
            it.sendMessage(timerStopMessage)
        }
    }

    open fun launchStart() {
        startingTask = kotlin.concurrent.timer(period = 50) {
            if(startingTime > 0) {
                startingTime--
                timerMessages[startingTime]?.let { msg ->
                    getPlayers().forEach {
                        it.sendMessage(msg)
                    }
                }
            } else {
                onStart()
                isStarted = true
                startingTask?.cancel()
            }
        }
    }

    override fun canPlayerJoin(player: Player): Boolean {
        return !isStarted
    }

    override fun isAvailable(): Boolean {
        return !isStarted
    }

    /**
     * It starts when the timer goes to 0. Asynchronous.
     */
    abstract fun onStart()
}