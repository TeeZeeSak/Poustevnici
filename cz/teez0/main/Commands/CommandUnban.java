package cz.teez0.main.Commands;

import cz.teez0.main.Functions.Ban;
import cz.teez0.main.Functions.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandUnban implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender instanceof Player && !Permissions.HasPermission((Player)sender, "unban")) {
         sender.sendMessage("§4Insufficient permission");
         return true;
      } else if (args.length != 1) {
         sender.sendMessage("§4You have error in your syntax! Usage: /unban <§6§lPlayer§r§4>");
         return true;
      } else {
         if (sender instanceof Player) {
            Ban.unbanPlayer(args[0], (Player)sender);
         } else if (sender instanceof ConsoleCommandSender) {
            Ban.unbanPlayer(args[0], (ConsoleCommandSender)sender);
         }

         return true;
      }
   }
}
