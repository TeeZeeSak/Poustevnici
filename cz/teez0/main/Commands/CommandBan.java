package cz.teez0.main.Commands;

import cz.teez0.main.main;
import cz.teez0.main.Functions.Ban;
import cz.teez0.main.Functions.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandBan implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender instanceof Player && !Permissions.HasPermission((Player)sender, "ban")) {
         sender.sendMessage("§4Insufficient permission");
         return true;
      } else if (args.length < 3) {
         sender.sendMessage("§4You have error in your syntax! Usage: /ban <§6§lPlayer§r§4> [§6§lDuration§r§4] <§6§lReason§r§4>");
         return true;
      } else {
         String durationClear = args[1];
         String reason = "";

         int duration;
         try {
            duration = Integer.parseInt(durationClear);
         } catch (NumberFormatException var10) {
            duration = 0;
         }

         int i;
         if (duration == 0) {
            for(i = 1; i < args.length; ++i) {
               reason = reason + " " + args[i];
            }
         } else {
            for(i = 2; i < args.length; ++i) {
               reason = reason + " " + args[i];
            }
         }

         StringBuilder builder = new StringBuilder(reason);
         builder.deleteCharAt(0);
         reason = builder.toString();
         Player targetPlayer;
         if (sender instanceof Player) {
            targetPlayer = ((main)main.getPlugin(main.class)).getServer().getPlayerExact(args[0]);
            if (targetPlayer instanceof Player) {
               Ban.banPlayer(targetPlayer, (Player)sender, reason, duration);
            } else {
               Ban.banPlayer(args[0], (Player)sender, reason, duration);
            }
         }

         if (sender instanceof ConsoleCommandSender) {
            targetPlayer = ((main)main.getPlugin(main.class)).getServer().getPlayerExact(args[0]);
            if (targetPlayer instanceof Player) {
               Ban.banPlayer(targetPlayer, (ConsoleCommandSender)sender, reason, duration);
            } else {
               Ban.banPlayer(args[0], (ConsoleCommandSender)sender, reason, duration);
            }
         }

         return true;
      }
   }
}
