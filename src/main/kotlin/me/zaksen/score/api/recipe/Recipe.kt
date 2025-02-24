package me.zaksen.score.api.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.score.api.recipe.entry.RecipeEntry

@Serializable
abstract class Recipe {
    @SerialName("output")
    abstract val output: RecipeEntry
}