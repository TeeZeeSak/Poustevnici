package cz.teez0.main.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerItemDamage implements Listener {
   @EventHandler
   public void onPlayerItemDamage(PlayerItemDamageEvent e) {
      ItemStack item = e.getItem();
      ItemMeta meta = item.getItemMeta();
      int durability = ((Damageable)meta).getDamage();
      int itemDurability = e.getItem().getType().getMaxDurability() - durability;
      int maxItemDurability = e.getItem().getType().getMaxDurability();
      if (itemDurability <= maxItemDurability / 100 * 2) {
         e.getPlayer().sendMessage("§aYour " + item.getType().toString().replace("_", " ").toLowerCase() + " §r§awill break soon! §8(§7uses left: §c" + itemDurability + "§8)");
      }

   }
}
