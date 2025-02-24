package me.zaksen.score.api.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.score.api.serialization.NamespacedKeyValue

@Serializable
data class EnchantmentSurrogate(
    @SerialName("key")
    val key: NamespacedKeyValue
)
