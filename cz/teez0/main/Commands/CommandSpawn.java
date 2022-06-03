package cz.teez0.main.Commands;

import cz.teez0.main.main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender instanceof Player) {
         Player player = (Player)sender;
         FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
         if (!config.contains("spawn.world")) {
            player.sendMessage("§cSpawn point has not been set yet!");
            return true;
         }

         World w = ((main)main.getPlugin(main.class)).getServer().getWorld((String)config.get("spawn.world"));
         double x = (Double)config.get("spawn.x");
         double y = (Double)config.get("spawn.y");
         double z = (Double)config.get("spawn.z");
         Location location = new Location(w, x, y, z);
         player.teleport(location);
      }

      return true;
   }
}
