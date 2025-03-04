package me.zaksen.score.util.ext

import org.bukkit.NamespacedKey

fun String.toNamespacedKey(): NamespacedKey {
    val split = this.split(":")

    if(split.size != 2) {
        throw IllegalArgumentException("Wrong string for creating NamespacedKey")
    }

    return NamespacedKey(split.first(), split.last())
}