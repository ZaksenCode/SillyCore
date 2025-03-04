package me.zaksen.score.api.recipe.entry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bukkit.inventory.ItemStack

@Serializable
@SerialName("Entry")
abstract class RecipeEntry {
    abstract fun asStack(): ItemStack
}