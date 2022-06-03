package cz.teez0.main.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
   @EventHandler
   public void onPlayerDie(PlayerDeathEvent e) {
      e.getEntity().sendMessage("§9Death location: §8[§7X: " + e.getEntity().getLocation().getBlockX() + "§8] " + "[§7Y: " + e.getEntity().getLocation().getBlockY() + "§8] " + "[§7Z: " + e.getEntity().getLocation().getBlockZ() + "§8]");
   }
}
