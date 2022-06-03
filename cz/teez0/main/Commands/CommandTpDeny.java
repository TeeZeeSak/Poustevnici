package cz.teez0.main.Commands;

import cz.teez0.main.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTpDeny implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender instanceof Player) {
         Player player = (Player)sender;
         if (!main.tpaRequests.containsKey(player)) {
            player.sendMessage("§cNo teleport requests pending!");
            return true;
         }

         Player requester = (Player)main.tpaRequests.get(player);
         if (requester != null) {
            requester.sendMessage("§6" + player.getName() + "§a has declined your request!");
            main.tpaRequests.remove(player);
         }
      }

      return true;
   }
}
