package cz.teez0.main.Events;

import cz.teez0.main.main;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
   @EventHandler
   public void onPlayerChat(AsyncPlayerChatEvent e) {
      Player player = e.getPlayer();
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      String prefix = "";
      List<String> groups = config.getStringList(player.getName() + ".groups");
      String message = e.getMessage();
      message = ChatColor.translateAlternateColorCodes('&', e.getMessage());
      e.setMessage(message);
      if (groups.size() != 0) {
         String group = (String)groups.get(groups.size() - 1);
         prefix = config.getString("groups." + group + ".prefix");
         if (prefix != "" && prefix != null) {
            e.setFormat(prefix + " " + player.getName() + " §8§o» §r" + e.getMessage());
         } else {
            e.setFormat(player.getName() + " §8§o» §r" + e.getMessage());
         }
      } else if (prefix != null) {
         e.setFormat(prefix + "" + player.getName() + " §f» §r" + e.getMessage());
      } else {
         e.setFormat(player.getName() + " §f» §r" + e.getMessage());
      }

   }
}
