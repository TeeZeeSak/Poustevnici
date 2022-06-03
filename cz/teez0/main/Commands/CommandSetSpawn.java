package cz.teez0.main.Commands;

import cz.teez0.main.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender instanceof Player) {
         if (!sender.getName().equals("TeeZ0x")) {
            sender.sendMessage("§4Insufficient permission");
            return true;
         }

         FileConfiguration config = ((main)main.getPlugin(main.class)).getConfig();
         Player player = (Player)sender;
         String w = player.getLocation().getWorld().getName();
         double x = (double)player.getLocation().getBlockX();
         double y = (double)player.getLocation().getBlockY();
         double z = (double)player.getLocation().getBlockZ();
         config.set("spawn.world", w);
         config.set("spawn.x", x);
         config.set("spawn.y", y);
         config.set("spawn.z", z);
         ((main)main.getPlugin(main.class)).saveConfig();
         sender.sendMessage("§aSpawn point set.");
      } else {
         sender.sendMessage("§4In-game command only!");
      }

      return true;
   }
}
