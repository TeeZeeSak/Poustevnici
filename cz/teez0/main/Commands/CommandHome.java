package cz.teez0.main.Commands;

import cz.teez0.main.main;
import cz.teez0.main.Files.Players;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandHome implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      FileConfiguration config = Players.getCustomConfig();
      if (sender instanceof Player) {
         Player player = (Player)sender;
         if (!config.contains(player.getName() + ".home.world")) {
            player.sendMessage("§cYou need to set home first! /sethome");
            return true;
         }

         World w = ((main)main.getPlugin(main.class)).getServer().getWorld((String)config.get(player.getName() + ".home.world"));
         double x = (Double)config.get(player.getName() + ".home.x");
         double y = (Double)config.get(player.getName() + ".home.y");
         double z = (Double)config.get(player.getName() + ".home.z");
         Location location = new Location(w, x, y, z);
         player.teleport(location);
         player.sendMessage("§9Woosh!");
      }

      return true;
   }
}
