package cz.teez0.main.Functions;

import cz.teez0.main.main;
import java.util.Iterator;
import java.util.List;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Permissions {
   public static void PermitPlayer(Player sender, String target, String permission) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      List<String> permissions = config.getStringList(target + ".permissions");
      if (!permissions.contains(permission)) {
         permissions.add(permission);
         config.set(target + ".permissions", permissions);
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + target + "§r§a has been permitted §6§l" + permission);
      } else {
         sender.sendMessage("§4Player $6§l" + target + "§r§4 has already been permitted §6§l" + permission);
      }

   }

   public static void PermitPlayer(ConsoleCommandSender sender, String target, String permission) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      List<String> permissions = config.getStringList(target + ".permissions");
      if (!permissions.contains(permission)) {
         permissions.add(permission);
         config.set(target + ".permissions", permissions);
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + target + "§r§a has been permitted §6§l" + permission);
      } else {
         sender.sendMessage("§4Player §6§l" + target + "§r§4 has already been permitted §6§l" + permission);
      }

   }

   public static void ForbidPlayer(Player sender, String target, String permission) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      List<String> permissions = config.getStringList(target + ".permissions");
      if (permissions.contains(permission)) {
         permissions.remove(permission);
         config.set(target + ".permissions", permissions);
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + target + "§r§a has been forbid §6§l" + permission);
      } else {
         sender.sendMessage("§4Player §6§l" + target + "§r§4 isn't permitted §6§l" + permission);
      }

   }

   public static void ForbidPlayer(ConsoleCommandSender sender, String target, String permission) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      List<String> permissions = config.getStringList(target + ".permissions");
      if (permissions.contains(permission)) {
         permissions.remove(permission);
         config.set(target + ".permissions", permissions);
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + target + "§r§a has been forbid §6§l" + permission);
      } else {
         sender.sendMessage("§4Player §6§l" + target + "§r§4 isn't permitted §6§l" + permission);
      }

   }

   public static void addGroup(Player sender, String target, String group) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      List<String> groups = config.getStringList(target + ".groups");
      if (!groups.contains(group)) {
         groups.add(group);
         config.set(target + ".groups", group);
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + target + "§r§a has been add to group §6§l" + group);
      } else {
         sender.sendMessage("§4Player §6§l" + target + "§r§4 is already member of §6§l" + group);
      }

   }

   public static void addGroup(ConsoleCommandSender sender, String target, String group) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      List<String> groups = config.getStringList(target + ".groups");
      if (!groups.contains(group)) {
         groups.add(group);
         config.set(target + ".groups", group);
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aPlayer §6§l" + target + "§r§a has been add to group §6§l" + group);
      } else {
         sender.sendMessage("§4Player §6§l" + target + "§r§4 is already member of §6§l" + group);
      }

   }

   public static void forceAddGroup(String target, String group) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      List<String> groups = config.getStringList(target + ".groups");
      if (!groups.contains(group)) {
         groups.add(group);
         config.set(target + ".groups", group);
         ((main)main.getPlugin(main.class)).saveConfig();
         ((main)main.getPlugin(main.class)).getLogger().info("§aPlayer §6§l" + target + "§r§a has been add to group §6§l" + group);
      } else {
         ((main)main.getPlugin(main.class)).getLogger().info("§4Player §6§l" + target + "§r§4 is already member of §6§l" + group);
      }

   }

   public static boolean HasPermission(Player target, String permission) {
      FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
      List<String> permissions = config.getStringList(target.getName() + ".permissions");
      if (permissions.contains(permission)) {
         return true;
      } else {
         List<String> groups = config.getStringList(target.getName() + ".groups");
         Iterator i$ = groups.iterator();

         List groupPermissions;
         do {
            if (!i$.hasNext()) {
               return false;
            }

            String group = (String)i$.next();
            groupPermissions = config.getStringList("groups." + group + ".permissions");
            if (groupPermissions.contains(permission)) {
               return true;
            }
         } while(!groupPermissions.contains("*"));

         return true;
      }
   }
}
