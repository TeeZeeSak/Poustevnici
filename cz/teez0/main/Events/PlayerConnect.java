package cz.teez0.main.Events;

import cz.teez0.main.main;
import cz.teez0.main.Functions.Ban;
import cz.teez0.main.Functions.Hrac;
import cz.teez0.main.Functions.Permissions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnect implements Listener {
   @EventHandler
   public void onPlayerJoin(PlayerJoinEvent e) {
      Player player = e.getPlayer();
      if (!player.hasPlayedBefore()) {
         Permissions.forceAddGroup(player.getName(), "default");
      }

      if (Ban.getPlayerBanned(player)) {
         FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
         long timestamp = System.currentTimeMillis() / 1000L;
         int banDuration = config.getInt(player.getName() + ".ban.duration");
         String banReason = config.getString(player.getName() + ".ban.reason");
         String admin = config.getString(player.getName() + ".ban.admin");
         String banID = config.getString(player.getName() + ".ban.banid");
         long banTimeStamp = config.getLong(player.getName() + ".ban.date");
         ((main)main.getPlugin(main.class)).getLogger().info("DEBUG: Current TimeStamp: " + timestamp + " banTimeStamp: " + banTimeStamp + " banDuration: " + banDuration * 60);
         String durationKeyWord;
         if (timestamp >= banTimeStamp + (long)(banDuration * 60)) {
            Ban.forceUnbanPlayer(e.getPlayer());
            durationKeyWord = "§6§l{player} §r$§se has just connected!";
            durationKeyWord.replace("{player}", e.getPlayer().getName());
            e.setJoinMessage(durationKeyWord);
            return;
         }

         if (banDuration != 0) {
            int banDurationSeconds = banDuration * 60;
            int banTimeSeconds = (int)banTimeStamp;
            int unbanTime = banDurationSeconds + banTimeSeconds;
            int timeUntilUnbanSeconds = unbanTime - (int)timestamp;
            ((main)main.getPlugin(main.class)).getLogger().info("Ban timestamp: " + banTimeSeconds + "\nban seconds: " + banDurationSeconds + "\nminutes until unban: " + timeUntilUnbanSeconds);
            String formattedDate = Ban.convertToDaysHoursMinutes((long)(timeUntilUnbanSeconds / 60));
            String durationKeyWord = "§4You are temporarily banned for §6§l" + formattedDate + " §r§4from this server! §r\n\n§7Banned by: §6§l" + admin + "§r\n§7Ban reason: §6§l" + banReason + "\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l" + banID;
            player.kickPlayer(durationKeyWord);
         } else {
            durationKeyWord = "§4You are permanently banned from this server! §r\n\n§7Banned by: §6§l" + admin + "§r\n§7Ban reason: §6§l" + banReason + "\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l" + banID;
            player.kickPlayer(durationKeyWord);
         }
      } else {
         String joinMessage = main.JoinMessage;
         joinMessage = joinMessage.replaceAll("\\{player\\}", e.getPlayer().getName());
         e.setJoinMessage(joinMessage);
         Hrac.addUserToActionBarTask(player);
      }

   }
}
