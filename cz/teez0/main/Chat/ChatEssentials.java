package cz.teez0.main.Chat;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatEssentials {
   private static final int CENTER_PX = 154;

   public static void sendCenteredMessage(Player player, String message) {
      if (message == null || message.equals("")) {
         player.sendMessage("");
      }

      message = ChatColor.translateAlternateColorCodes('&', message);
      int messagePxSize = 0;
      boolean previousCode = false;
      boolean isBold = false;
      char[] arr$ = message.toCharArray();
      int toCompensate = arr$.length;

      int spaceLength;
      for(spaceLength = 0; spaceLength < toCompensate; ++spaceLength) {
         char c = arr$[spaceLength];
         if (c == 167) {
            previousCode = true;
         } else if (previousCode) {
            previousCode = false;
            if (c != 'l' && c != 'L') {
               isBold = false;
            } else {
               isBold = true;
            }
         } else {
            FontEnum.DefaultFontInfo dFI = FontEnum.DefaultFontInfo.getDefaultFontInfo(c);
            messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
            ++messagePxSize;
         }
      }

      int halvedMessageSize = messagePxSize / 2;
      toCompensate = 154 - halvedMessageSize;
      spaceLength = FontEnum.DefaultFontInfo.SPACE.getLength() + 1;
      int compensated = 0;

      StringBuilder sb;
      for(sb = new StringBuilder(); compensated < toCompensate; compensated += spaceLength) {
         sb.append(" ");
      }

      player.sendMessage(sb.toString() + message);
   }

   public static void sendChallengeCompletionMessage(Player player, String header, String body) {
      String message_splitter = "§b§l--------------------";
      TextComponent message_header = new TextComponent("§a§lCHALLENGE COMPLETED");
      TextComponent message_body = new TextComponent("§7§l" + body);
      message_header.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(header)).create()));
      message_body.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(header)).create()));
      sendCenteredMessage(player, message_splitter);
      sendComponentedCenteredMessage(player, message_header);
      sendComponentedCenteredMessage(player, message_body);
      sendCenteredMessage(player, message_splitter);
   }

   public static void sendComponentedCenteredMessage(Player player, TextComponent message) {
      String clearMessage = message.getText();
      if (clearMessage == null || clearMessage.equals("")) {
         player.sendMessage("");
      }

      clearMessage = ChatColor.translateAlternateColorCodes('&', clearMessage);
      int messagePxSize = 0;
      boolean previousCode = false;
      boolean isBold = false;
      char[] arr$ = clearMessage.toCharArray();
      int toCompensate = arr$.length;

      int spaceLength;
      for(spaceLength = 0; spaceLength < toCompensate; ++spaceLength) {
         char c = arr$[spaceLength];
         if (c == 167) {
            previousCode = true;
         } else if (previousCode) {
            previousCode = false;
            if (c != 'l' && c != 'L') {
               isBold = false;
            } else {
               isBold = true;
            }
         } else {
            FontEnum.DefaultFontInfo dFI = FontEnum.DefaultFontInfo.getDefaultFontInfo(c);
            messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
            ++messagePxSize;
         }
      }

      int halvedMessageSize = messagePxSize / 2;
      toCompensate = 154 - halvedMessageSize;
      spaceLength = FontEnum.DefaultFontInfo.SPACE.getLength() + 1;
      int compensated = 0;

      StringBuilder sb;
      for(sb = new StringBuilder(); compensated < toCompensate; compensated += spaceLength) {
         sb.append(" ");
      }

      message.setText(sb.toString() + clearMessage);
      player.spigot().sendMessage(message);
   }
}
