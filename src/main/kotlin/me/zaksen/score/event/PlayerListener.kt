package me.zaksen.score.event

import me.zaksen.score.api.item.ItemRegistry
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemConsumeEvent

class PlayerListener(
    private val itemRegistry: ItemRegistry
): Listener {
    @EventHandler
    private fun processCustomItemUsage(event: PlayerInteractEvent) {
        val stack = event.item ?: return
        val customItem = itemRegistry.getItem(stack) ?: return

        customItem.onUse(event)
    }

    @EventHandler
    private fun processCustomItemHitting(event: EntityDamageByEntityEvent) {
        val stack = if(event.damager is LivingEntity) {
            (event.damager as LivingEntity).activeItem
        } else {
            return
        }
        val customItem = itemRegistry.getItem(stack) ?: return

        customItem.onHit(event)
    }

    @EventHandler
    private fun processCustomItemEntityUsage(event: PlayerInteractAtEntityEvent) {
        val stack = event.player.inventory.getItem(event.hand)
        val customItem = itemRegistry.getItem(stack) ?: return

        customItem.onUseWithEntity(event)
    }

    @EventHandler
    private fun processCustomItemConsuming(event: PlayerItemConsumeEvent) {
        val stack = event.item
        val customItem = itemRegistry.getItem(stack) ?: return

        customItem.onConsume(event)
    }
}