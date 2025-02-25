package me.zaksen.score.api.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.bukkit.inventory.EquipmentSlotGroup

class EquipmentSlotGroupSerializer: KSerializer<EquipmentSlotGroup> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "EquipmentSlotGroup",
        PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): EquipmentSlotGroup {
        return EquipmentSlotGroup.getByName(decoder.decodeString().lowercase())!!
    }

    override fun serialize(encoder: Encoder, value: EquipmentSlotGroup) {
        encoder.encodeString(value.toString())
    }
}

/** Data type for easier serialization EquipmentSlotGroup */
typealias EquipmentSlotGroupValue = @Serializable(EquipmentSlotGroupSerializer::class) EquipmentSlotGroup