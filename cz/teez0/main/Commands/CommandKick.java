package cz.teez0.main.Commands;

import cz.teez0.main.main;
import cz.teez0.main.Functions.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandKick implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender instanceof Player && !Permissions.HasPermission((Player)sender, "kick")) {
         sender.sendMessage("§4Insufficient permission");
         return true;
      } else if (args.length < 2) {
         sender.sendMessage("§4You have error in your syntax! Usage: /kick <§6§lPlayer§r§4> <§6§lReason§r§4>");
         return true;
      } else {
         String reason = "";

         for(int i = 1; i < args.length; ++i) {
            reason = reason + " " + args[i];
         }

         StringBuilder builder = new StringBuilder(reason);
         builder.deleteCharAt(0);
         reason = builder.toString();
         Player targetPlayer;
         if (sender instanceof Player) {
            targetPlayer = ((main)main.getPlugin(main.class)).getServer().getPlayerExact(args[0]);
            if (targetPlayer instanceof Player && targetPlayer != null) {
               targetPlayer.kickPlayer("§4You have been kicked!\n\n§7Reason: §6§l" + reason + "\n§r§7Kicked by: §6§l" + sender.getName() + "\n\n§r§7Find out more: §bhttps://teez0.cz/appeal");
            } else {
               sender.sendMessage("§7Player §6§l" + args[0] + " §r§7is not online.");
            }
         }

         if (sender instanceof ConsoleCommandSender) {
            targetPlayer = ((main)main.getPlugin(main.class)).getServer().getPlayerExact(args[0]);
            if (targetPlayer instanceof Player) {
               targetPlayer.kickPlayer("§4You have been kicked!\n\n§7Reason: §6§l" + reason + "\n§r§7Kicked by: §6§lConsole\n\n§r§7Find out more: §bhttps://teez0.cz/appeal");
            } else {
               sender.sendMessage("§7Player §6§l" + targetPlayer.getName() + " §r§7is not online.");
            }
         }

         return true;
      }
   }
}
