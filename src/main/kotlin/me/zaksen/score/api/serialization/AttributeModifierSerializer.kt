package me.zaksen.score.api.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.zaksen.score.api.serialization.surrogate.AttributeModifierSurrogate
import org.bukkit.attribute.AttributeModifier

class AttributeModifierSerializer : KSerializer<AttributeModifier> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "org.bukkit.attribute.AttributeModifier",
        AttributeModifierSurrogate.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): AttributeModifier {
        val surrogate = decoder.decodeSerializableValue(AttributeModifierSurrogate.serializer())
        return AttributeModifier(
            surrogate.key,
            surrogate.amount,
            surrogate.operation,
            surrogate.slot
        )
    }

    override fun serialize(encoder: Encoder, value: AttributeModifier) {
        val surrogate = AttributeModifierSurrogate(
            value.key,
            value.amount,
            value.operation,
            value.slotGroup
        )

        encoder.encodeSerializableValue(
            AttributeModifierSurrogate.serializer(),
            surrogate
        )
    }
}

/** Data type for easier serialization AttributeModifier */
typealias AttributeModifierValue = @Serializable(AttributeModifierSerializer::class) AttributeModifier