package me.zaksen.score.api.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.score.api.serialization.ItemMetaValue

@Serializable
@SerialName("ItemStack")
data class ItemStackSurrogate(
    @SerialName("type")
    val type: String,

    @SerialName("amount")
    val amount: Int,

    @SerialName("meta")
    val meta: ItemMetaValue
)
