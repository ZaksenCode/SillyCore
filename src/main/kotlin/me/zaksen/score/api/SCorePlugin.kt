package me.zaksen.score.api

import me.zaksen.score.SillyCore
import me.zaksen.score.api.addon.SCoreAddon
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

abstract class SCorePlugin: JavaPlugin(), SCoreAddon {

    override fun onLoad() {
        SillyCore.get().registerAddon(this)
    }

    /**
     * Method for simply registering EventListener's
     * @param listener Instance of the class which is responsible for events.
     * @see org.bukkit.plugin.PluginManager.registerEvents
     */
    protected fun registerEvents(listener: Listener) {
        server.pluginManager.registerEvents(listener, this)
    }
}