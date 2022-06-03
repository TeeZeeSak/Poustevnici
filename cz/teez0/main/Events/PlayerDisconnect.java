package cz.teez0.main.Events;

import cz.teez0.main.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDisconnect implements Listener {
   @EventHandler
   public void onPlayerDisconnect(PlayerQuitEvent e) {
      Player player = e.getPlayer();
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      String leaveMessage = main.LeaveMessage;
      leaveMessage = leaveMessage.replaceAll("\\{player\\}", e.getPlayer().getName());
      e.setQuitMessage(leaveMessage);
   }
}
