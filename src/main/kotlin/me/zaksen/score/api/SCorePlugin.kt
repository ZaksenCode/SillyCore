package me.zaksen.score.api

import me.zaksen.score.api.addon.SCoreAddon
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

abstract class SCorePlugin: JavaPlugin(), SCoreAddon {
    /**
     * Method for simply registering EventListener's
     * @param listener Instance of the class which is responsible for events.
     * @see org.bukkit.plugin.PluginManager.registerEvents
     */
    protected fun registerEvents(listener: Listener) {
        Bukkit.getPluginManager().registerEvents(listener, this)
    }

    /**
     * Function that should perform the functionality responsible for reloading this addon/plugin
     */
    protected open fun reload() {

    }
}