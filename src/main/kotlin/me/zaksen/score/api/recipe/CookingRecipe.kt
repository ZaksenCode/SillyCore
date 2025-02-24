package me.zaksen.score.api.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.score.api.recipe.entry.RecipeEntry

@Serializable
data class CookingRecipe(
    @SerialName("output")
    override val output: RecipeEntry,

    @SerialName("input")
    val input: RecipeEntry,

    @SerialName("experience")
    val experience: Float,

    @SerialName("cooking_time")
    val cookingTime: Int,

    @SerialName("exact_match")
    val exactMatch: Boolean
): Recipe()