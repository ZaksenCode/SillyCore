package me.zaksen.score.api.recipe.entry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.score.api.serialization.ItemStackValue
import org.bukkit.inventory.ItemStack

@Serializable
@SerialName("StackEntry")
class StackEntry(
    @SerialName("entry")
    val entry: ItemStackValue
): RecipeEntry() {
    override fun asStack(): ItemStack {
        return entry.clone()
    }
}