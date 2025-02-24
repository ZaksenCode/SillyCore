package me.zaksen.score.api.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.score.api.recipe.entry.MaterialEntry
import me.zaksen.score.api.recipe.entry.RecipeEntry
import org.bukkit.Material

@Serializable
@SerialName("ShapedRecipe")
data class ShapedRecipe(
    @SerialName("output")
    override val output: RecipeEntry,

    @SerialName("grid")
    val grid: List<String> = listOf(
        "AAA",
        "AAA",
        "AAA"
    ),

    @SerialName("grid_resolver")
    val gridResolve: Map<Char, RecipeEntry> = mapOf(
        Pair('A', MaterialEntry(Material.STICK))
    )
): Recipe()