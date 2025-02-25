package me.zaksen.score.api.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.score.api.serialization.EquipmentSlotGroupValue
import me.zaksen.score.api.serialization.NamespacedKeyValue
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.EquipmentSlotGroup

@Serializable
@SerialName("AttributeModifier")
data class AttributeModifierSurrogate(
    @SerialName("key")
    val key: NamespacedKeyValue,

    @SerialName("amount")
    val amount: Double,

    @SerialName("operation")
    val operation: AttributeModifier.Operation,

    @SerialName("slot")
    val slot: EquipmentSlotGroupValue
)
