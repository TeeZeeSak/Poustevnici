package cz.teez0.main.Functions;

import cz.teez0.main.main;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Ban {
   public static boolean getPlayerBanned(Player player) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      return config.getBoolean(player.getName() + ".ban.banned");
   }

   public static boolean getPlayerBanned(String player) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      return config.getBoolean(player + ".ban.banned");
   }

   public static String getBanID() {
      Random obj = new Random();
      int rand_num = obj.nextInt(16777216);
      String banID = String.format("#%06x", rand_num);
      return banID.toUpperCase();
   }

   public static String convertToDaysHoursMinutes(long minutes) {
      int day = (int)TimeUnit.MINUTES.toDays(minutes);
      long hours = TimeUnit.MINUTES.toHours(minutes) - (long)(day * 24);
      long minute = TimeUnit.MINUTES.toMinutes(minutes) - TimeUnit.MINUTES.toHours(minutes) * 60L;
      String result = "";
      if (day != 0) {
         result = result + day;
         if (day == 1) {
            result = result + " day";
         } else {
            result = result + " days";
         }
      }

      if (hours != 0L) {
         result = result + hours;
         if (hours == 1L) {
            result = result + " hour";
         } else {
            result = result + " hours";
         }
      }

      if (minute != 0L) {
         result = result + minute;
         if (minute == 1L) {
            result = result + " minute";
         } else {
            result = result + " minutes";
         }
      }

      return result.length() == 0 ? "less than a minute" : result;
   }

   public static void banPlayer(Player player, Player sender, String reason, @Nullable int duration) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      if (!config.getBoolean(player.getName() + ".immunity")) {
         String banID = getBanID();
         String durationKeyWord;
         if (duration == 0) {
            durationKeyWord = "§4You are permanently banned from this server! §r\n\n§7Banned by: §6§l" + sender.getName() + "§r\n§7Ban reason: §6§l" + reason + "\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l" + banID;
         } else {
            Calendar now = Calendar.getInstance();
            now.add(12, duration);
            String formattedDate = convertToDaysHoursMinutes((long)duration);
            durationKeyWord = "§4You are temporarily banned for §6§l" + formattedDate + " §r§4from this server! §r\n\n§7Banned by: §6§l" + sender.getName() + "§r\n§7Ban reason: §6§l" + reason + "\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l" + banID;
         }

         player.kickPlayer(durationKeyWord);
         long timestamp = System.currentTimeMillis() / 1000L;
         config.set(player.getName() + ".ban.date", timestamp);
         config.set(player.getName() + ".ban.banned", true);
         config.set(player.getName() + ".ban.admin", sender.getName());
         config.set(player.getName() + ".ban.duration", duration);
         config.set(player.getName() + ".ban.reason", reason);
         config.set(player.getName() + ".ban.banid", banID);
         config.set(player.getName() + ".ban.type", "Online");
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + player.getName() + "§r§a has been banned.");
      } else {
         sender.sendMessage("§4Couldn't ban player §6§l" + player.getName() + "§r§4 because they have immunity.");
      }

   }

   public static void unbanPlayer(String player, Player sender) {
      if (getPlayerBanned(player)) {
         ((main)main.getPlugin(main.class)).getLogger().info("§7Player §6§l" + player + " §r§7has been unbanned by §6§l" + sender.getName() + "§r§7!");
         FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
         config.set(player + ".ban.banned", false);
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§7Player §6§l" + player + " §r§7has been unbanned");
      } else {
         sender.sendMessage("§7Player §6§l" + player + " §r§7has not been banned");
      }

   }

   public static void unbanPlayer(String player, ConsoleCommandSender sender) {
      if (getPlayerBanned(player)) {
         ((main)main.getPlugin(main.class)).getLogger().info("§7Player §6§l" + player + " §r§7has been unbanned by §6§lConsole§r§7!");
         FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
         config.set(player + ".ban.banned", false);
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§7Player §6§l" + player + " §r§7has been unbanned");
      } else {
         sender.sendMessage("§7Player §6§l" + player + " §r§7has not been banned");
      }

   }

   public static void forceUnbanPlayer(Player player) {
      if (getPlayerBanned(player)) {
         ((main)main.getPlugin(main.class)).getLogger().info("Player " + player.getName() + " has been unbanned!");
         FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
         config.set(player.getName() + ".ban.banned", false);
         ((main)main.getPlugin(main.class)).saveConfig();
      }

   }

   public static void banPlayer(String player, Player sender, String reason, @Nullable int duration) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      if (!config.getBoolean(player + ".immunity")) {
         String banID = getBanID();
         if (duration == 0) {
            (new StringBuilder()).append("§4You are permanently banned from this server! §r\n\n§7Banned by: §6§l").append(sender.getName()).append("§r\n§7Ban reason: §6§l").append(reason).append("\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l").append(banID).toString();
         } else {
            Calendar now = Calendar.getInstance();
            now.add(12, duration);
            String formattedDate = convertToDaysHoursMinutes((long)duration);
            (new StringBuilder()).append("§4You are temporarily banned for §6§l").append(formattedDate).append(" §r§4from this server! §r\n\n§7Banned by: §6§l").append(sender.getName()).append("§r\n§7Ban reason: §6§l").append(reason).append("\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l").append(banID).toString();
         }

         long timestamp = System.currentTimeMillis() / 1000L;
         config.set(player + ".ban.date", timestamp);
         config.set(player + ".ban.banned", true);
         config.set(player + ".ban.admin", sender.getName());
         config.set(player + ".ban.duration", duration);
         config.set(player + ".ban.reason", reason);
         config.set(player + ".ban.banid", banID);
         config.set(player + ".ban.type", "Offline");
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + player + "§r§a has been banned.");
      } else {
         sender.sendMessage("§4Couldn't ban player §6§l" + player + "§r§4 because they have immunity.");
      }

   }

   public static void banPlayer(Player player, ConsoleCommandSender sender, String reason, @Nullable int duration) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      if (!config.getBoolean(player.getName() + ".immunity")) {
         String banID = getBanID();
         String durationKeyWord;
         if (duration == 0) {
            durationKeyWord = "§4You are permanently banned from this server! §r\n\n§7Banned by: §6§lConsole§r\n§7Ban reason: §6§l" + reason + "\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l" + banID;
         } else {
            Calendar now = Calendar.getInstance();
            now.add(12, duration);
            String formattedDate = convertToDaysHoursMinutes((long)duration);
            durationKeyWord = "§4You are temporarily banned for §6§l" + formattedDate + " §r§4from this server! §r\n\n§7Banned by: §6§lConsole§r\n§7Ban reason: §6§l" + reason + "\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l" + banID;
         }

         player.kickPlayer(durationKeyWord);
         long timestamp = System.currentTimeMillis() / 1000L;
         config.set(player.getName() + ".ban.date", timestamp);
         config.set(player.getName() + ".ban.banned", true);
         config.set(player.getName() + ".ban.admin", "Console");
         config.set(player.getName() + ".ban.duration", duration);
         config.set(player.getName() + ".ban.reason", reason);
         config.set(player.getName() + ".ban.banid", banID);
         config.set(player.getName() + ".ban.type", "Offline");
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + player.getName() + "§r§a has been banned.");
      } else {
         sender.sendMessage("§4Couldn't ban player §6§l" + player.getName() + "§r§4 because they have immunity.");
      }

   }

   public static void banPlayer(String player, ConsoleCommandSender sender, String reason, @Nullable int duration) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      if (!config.getBoolean(player + ".immunity")) {
         String banID = getBanID();
         if (duration == 0) {
            (new StringBuilder()).append("§4You are permanently banned from this server! §r\n\n§7Banned by: §6§lConsole§r\n§7Ban reason: §6§l").append(reason).append("\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l").append(banID).toString();
         } else {
            Calendar now = Calendar.getInstance();
            now.add(12, duration);
            String formattedDate = convertToDaysHoursMinutes((long)duration);
            (new StringBuilder()).append("§4You are temporarily banned for §6§l").append(formattedDate).append(" §r§4from this server! §r\n\n§7Banned by: §6§lConsole§r\n§7Ban reason: §6§l").append(reason).append("\n§r§7Find out more: §bhttps://teez0.cz/appeal\n\n§7Ban ID: §r§6§l").append(banID).toString();
         }

         long timestamp = System.currentTimeMillis() / 1000L;
         config.set(player + ".ban.date", timestamp);
         config.set(player + ".ban.banned", true);
         config.set(player + ".ban.admin", "Console");
         config.set(player + ".ban.duration", duration);
         config.set(player + ".ban.reason", reason);
         config.set(player + ".ban.banid", banID);
         config.set(player + ".ban.type", "Offline");
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + player + "§r§a has been banned.");
      } else {
         sender.sendMessage("§4Couldn't ban player §6§l" + player + "§r§4 because they have immunity.");
      }

   }
}
