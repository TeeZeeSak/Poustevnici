package cz.teez0.main.Commands;

import cz.teez0.main.Chat.ChatEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHelp implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      ChatEssentials.sendCenteredMessage((Player)sender, ChatColor.AQUA + "" + ChatColor.BOLD + "" + "--------------------");
      ChatEssentials.sendCenteredMessage((Player)sender, ChatColor.WHITE + "" + ChatColor.BOLD + "PRIKAZY:");
      sender.sendMessage(ChatColor.YELLOW + "/help - toto");
      sender.sendMessage(ChatColor.YELLOW + "/tpa <Hrac> - Posle TPA zadost hraci");
      sender.sendMessage(ChatColor.YELLOW + "/sethome - Vytvori domovsky bod");
      sender.sendMessage(ChatColor.YELLOW + "/home - Teleportuje na domovsky bod");
      ChatEssentials.sendCenteredMessage((Player)sender, ChatColor.AQUA + "" + ChatColor.BOLD + "" + "--------------------");
      return true;
   }
}
