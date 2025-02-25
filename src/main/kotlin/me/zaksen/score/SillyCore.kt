package me.zaksen.score

import me.zaksen.score.api.addon.SCoreAddon
import me.zaksen.score.api.item.ItemRegistry
import me.zaksen.score.api.recipe.RecipeRegistry
import me.zaksen.score.event.PlayerListener
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import org.incendo.cloud.execution.ExecutionCoordinator
import org.incendo.cloud.paper.PaperCommandManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SillyCore: JavaPlugin() {

    private val addons = mutableSetOf<SCoreAddon>()
    private val logger: Logger = LoggerFactory.getLogger(name)
    private val keys = SillyKeys(this)

    private val recipeRegistry = RecipeRegistry(addons)
    private val itemRegistry = ItemRegistry(addons, keys.itemId)

    private val commandManager by lazy {
        PaperCommandManager.builder()
            .executionCoordinator(ExecutionCoordinator.asyncCoordinator())
            .buildOnEnable(this)
    }

    override fun onLoad() {
        INSTANCE = this
    }

    override fun onEnable() {
        logger.info("Loading SCore addons:")
        addons.forEach {
            it.loadItems(itemRegistry, keys.itemId)
            it.loadRecipes(recipeRegistry)
            it.loadCommands(commandManager)
            logger.info("- ${it.getAddonName()}")
        }

        server.pluginManager.registerEvents(PlayerListener(itemRegistry), this)
    }

    override fun onDisable() {

    }

    /**
     * Direct use is not recommended.
     */
    fun registerAddon(addon: SCoreAddon) {
        addons.add(addon)
    }

    companion object {
        private lateinit var INSTANCE: SillyCore

        /**
         * Returns Instance to an object of class SkillifyCore
         */
        fun get(): SillyCore {
            return INSTANCE
        }
    }
}

private class SillyKeys(
    plugin: JavaPlugin
) {
    val itemId: NamespacedKey = NamespacedKey(plugin, "item_id")
}