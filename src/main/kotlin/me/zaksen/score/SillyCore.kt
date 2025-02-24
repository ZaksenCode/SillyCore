package me.zaksen.score

import me.zaksen.score.api.SCorePlugin
import me.zaksen.score.api.addon.SCoreAddon
import me.zaksen.score.api.item.ItemRegistry
import me.zaksen.score.api.recipe.RecipeRegistry
import me.zaksen.score.event.PlayerListener
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SillyCore: SCorePlugin() {

    private val addons = mutableSetOf<SCoreAddon>()
    private val logger: Logger = LoggerFactory.getLogger(name)
    private val keys = SillyKeys(this)

    private val recipeRegistry = RecipeRegistry(addons)
    private val itemRegistry = ItemRegistry(addons, keys.itemId)

    override fun getAddonName(): String {
        return "SCore"
    }

    override fun onEnable() {
        logger.info("Loading SCore addons:")
        addons.forEach {
            it.loadItems(itemRegistry, keys.itemId)
            it.loadRecipes(recipeRegistry)
            logger.info("- ${it.getAddonName()}")
        }

        registerEvents(PlayerListener(itemRegistry))
    }

    override fun onDisable() {

    }
}

private class SillyKeys(
    plugin: JavaPlugin
) {
    val itemId: NamespacedKey = NamespacedKey(plugin, "item_id")
}