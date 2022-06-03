package cz.teez0.main.Commands;

import cz.teez0.main.main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTpa implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender instanceof Player) {
         final Player player = (Player)sender;
         if (args.length == 0) {
            player.sendMessage("§9Usage: /tpa <Player>");
            return true;
         }

         final Player target = ((main)main.getPlugin(main.class)).getServer().getPlayerExact(args[0]);
         if (target == player) {
            player.sendMessage("§9Woosh!");
            return true;
         }

         if (target != null) {
            TextComponent message = new TextComponent(player.getName() + " wants to teleport. Accept? ");
            TextComponent YES = new TextComponent("§aYES");
            TextComponent NO = new TextComponent("§cNO");
            TextComponent openingBracket = new TextComponent("§7[");
            TextComponent closingBracket = new TextComponent("§7] ");
            YES.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tpaccept"));
            YES.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Accept teleport")).create()));
            NO.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Repeal teleport")).create()));
            NO.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tpdeny"));
            message.addExtra(openingBracket);
            message.addExtra(YES);
            message.addExtra(closingBracket);
            message.addExtra(openingBracket);
            message.addExtra(NO);
            message.addExtra(closingBracket);
            player.sendMessage("§9Request sent!");
            target.spigot().sendMessage(message);
            main.tpaRequests.put(target, player);
            Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(main.class), new Runnable() {
               public void run() {
                  main.tpaRequests.remove(target);
                  if (main.tpaRequests.containsKey(player)) {
                     player.sendMessage("§7Teleport request timed out!");
                  }

               }
            }, 600L);
         } else {
            player.sendMessage("§cNo such player online!");
         }
      }

      return true;
   }
}
