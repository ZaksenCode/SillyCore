package me.zaksen.score.api.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.score.api.recipe.entry.RecipeEntry
import me.zaksen.score.api.recipe.entry.StackEntry
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

@Serializable
@SerialName("ShapelessRecipe")
data class ShapelessRecipe(
    @SerialName("output")
    override val output: RecipeEntry,

    @SerialName("ingredients")
    val ingredients: List<RecipeEntry> = listOf(
        StackEntry(ItemStack(Material.STONE))
    )
): Recipe()