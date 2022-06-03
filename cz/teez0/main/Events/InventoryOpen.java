package cz.teez0.main.Events;

import cz.teez0.main.main;
import cz.teez0.main.Chat.ChatEssentials;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryOpen implements Listener {
   @EventHandler
   public void onInventoryOpenEvent(InventoryOpenEvent e) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      Player player = (Player)e.getPlayer();
      if (!config.getBoolean(player.getName() + ".challenges.OpenChest") && (e.getInventory().getHolder() instanceof Chest || e.getInventory().getHolder() instanceof DoubleChest)) {
         config.set(player.getName() + ".challenges.OpenChest", true);
         ((main)main.getPlugin(main.class)).saveConfig();
         player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
         ChatEssentials.sendChallengeCompletionMessage(player, "Take a look inside the chest", "What's in there?");
      }

   }
}
