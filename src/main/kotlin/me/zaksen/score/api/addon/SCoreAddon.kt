package me.zaksen.score.api.addon

import me.zaksen.score.api.item.ItemRegistry
import me.zaksen.score.api.recipe.RecipeRegistry
import org.bukkit.NamespacedKey

/**
 * Interface that shows SCore that this is its addon.
 * Used by SCore to create some semblance of CallBack's.
 */
interface SCoreAddon {
    /**
     * Should return the name of addon for SCore.
     * @return name of addon
     */
    fun getAddonName(): String
    /**
     * Method to be called when loading or reloading recipes.
     * @param registry A recipe registry where you can register recipes.
     *
     * @see RecipeRegistry
     */
    fun loadRecipes(registry: RecipeRegistry) {}
    /**
     * Method to be called when loading or reloading custom items.
     * @param registry an item registry where you can register custom items and default stacks.
     *
     * @see ItemRegistry
     */
    fun loadItems(registry: ItemRegistry, itemIdKey: NamespacedKey) {}
}