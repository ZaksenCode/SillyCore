package me.zaksen.score.api.recipe.entry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

@Serializable
@SerialName("MaterialEntry")
class MaterialEntry(
    private val entry: Material
): RecipeEntry() {
    override fun asStack(): ItemStack {
        return ItemStack(entry)
    }
}