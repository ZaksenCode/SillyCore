package me.zaksen.score.api.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("NamespacedKey")
data class NamespacedKeySurrogate(
    val namespace: String,
    val key: String
)
