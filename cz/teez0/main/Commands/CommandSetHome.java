package cz.teez0.main.Commands;

import cz.teez0.main.Files.Players;
import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandSetHome implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      FileConfiguration config = Players.getCustomConfig();
      if (sender instanceof Player) {
         Player player = (Player)sender;
         String w = player.getLocation().getWorld().getName();
         double x = (double)player.getLocation().getBlockX();
         double y = (double)player.getLocation().getBlockY();
         double z = (double)player.getLocation().getBlockZ();
         config.set(player.getName() + ".home.world", w);
         config.set(player.getName() + ".home.x", x);
         config.set(player.getName() + ".home.y", y);
         config.set(player.getName() + ".home.z", z);

         try {
            Players.save();
         } catch (IOException var15) {
            var15.printStackTrace();
         }

         player.sendMessage("§aHome set!");
      }

      return true;
   }
}
