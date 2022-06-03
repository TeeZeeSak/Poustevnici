package cz.teez0.main.Functions;

import cz.teez0.main.main;
import cz.teez0.main.Files.Classes;
import cz.teez0.main.Files.PlayerBlocks;
import cz.teez0.main.Files.Players;
import java.io.IOException;
import java.text.DecimalFormat;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Hrac {
   public static final int CLASS_MINER = 0;
   public static final int CLASS_HUNTER = 1;
   public static final int CLASS_LUMBERJACK = 2;

   public static int getLevel(int CLASS, Player player) {
      FileConfiguration config = Players.getCustomConfig();
      switch(CLASS) {
      case 0:
         int minerLevel = config.getInt(player.getName() + ".miner.level");
         if (minerLevel != 0) {
            return minerLevel;
         }

         config.set(player.getName() + ".miner.level", 1);

         try {
            Players.save();
         } catch (IOException var9) {
            var9.printStackTrace();
         }

         return minerLevel;
      case 1:
         int hunterLevel = config.getInt(player.getName() + ".hunter.level");
         if (hunterLevel != 0) {
            return hunterLevel;
         }

         config.set(player.getName() + ".hunter.level", 1);

         try {
            Players.save();
         } catch (IOException var8) {
            var8.printStackTrace();
         }

         return hunterLevel;
      case 2:
         int lumberjack = config.getInt(player.getName() + ".lumberjack.level");
         if (lumberjack != 0) {
            return lumberjack;
         }

         config.set(player.getName() + ".lumberjack.level", 1);

         try {
            Players.save();
         } catch (IOException var7) {
            var7.printStackTrace();
         }

         return lumberjack;
      default:
         return 0;
      }
   }

   public static double getXP(int CLASS, Player player) {
      FileConfiguration config = Players.getCustomConfig();
      switch(CLASS) {
      case 0:
         double minerLevel = config.getDouble(player.getName() + ".miner.xp");
         if (minerLevel != 0.0D) {
            return minerLevel;
         }

         config.set(player.getName() + ".miner.xp", 0);

         try {
            Players.save();
         } catch (IOException var12) {
            var12.printStackTrace();
         }

         return minerLevel;
      case 1:
         double hunterLevel = config.getDouble(player.getName() + ".hunter.xp");
         if (hunterLevel != 0.0D) {
            return hunterLevel;
         }

         config.set(player.getName() + ".hunter.xp", 0);

         try {
            Players.save();
         } catch (IOException var11) {
            var11.printStackTrace();
         }

         return hunterLevel;
      case 2:
         double lumberjack = config.getDouble(player.getName() + ".lumberjack.xp");
         if (lumberjack != 0.0D) {
            return lumberjack;
         }

         config.set(player.getName() + ".lumberjack.xp", 0);

         try {
            Players.save();
         } catch (IOException var10) {
            var10.printStackTrace();
         }

         return lumberjack;
      default:
         return 0.0D;
      }
   }

   public static double getNextLevel(int CLASS, Player player) {
      FileConfiguration config = Classes.getCustomConfig();
      double currentLevelXP = 0.0D;
      double nextLevelXP;
      switch(CLASS) {
      case 0:
         nextLevelXP = (double)(25 * getLevel(0, player) * (1 + getLevel(0, player)));
         currentLevelXP = getXP(0, player);
         return nextLevelXP - currentLevelXP;
      case 1:
         nextLevelXP = (double)(5 * getLevel(1, player) * (1 + getLevel(1, player)));
         currentLevelXP = getXP(1, player);
         return nextLevelXP - currentLevelXP;
      case 2:
         nextLevelXP = (double)(3 * getLevel(2, player) * (1 + getLevel(2, player)));
         currentLevelXP = getXP(2, player);
         return nextLevelXP - currentLevelXP;
      default:
         return 0.0D;
      }
   }

   public static void addUserToActionBarTask(final Player p) {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getPlugin(main.class), new Runnable() {
         public void run() {
            Hrac.sendTablist(p);
            if (main.actionBar.containsKey(p)) {
               if ((Integer)main.actionBar.get(p) == 0) {
                  p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4§l" + (int)p.getHealth() + "❤ §r§8[§8⛏ §7" + Hrac.getLevel(0, p) + " §7" + Hrac.getXP(0, p) + "§8/§7" + Hrac.getNextLevelXP(0, p) + "§8] §a" + p.getAttribute(Attribute.GENERIC_ARMOR).getValue() + "❈ Defense"));
               } else if ((Integer)main.actionBar.get(p) == 1) {
                  p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4§l" + (int)p.getHealth() + "❤ §r§8[§c⚔ §7" + Hrac.getLevel(1, p) + " §7" + Hrac.getXP(1, p) + "§8/§7" + Hrac.getNextLevelXP(1, p) + "§8] §a" + p.getAttribute(Attribute.GENERIC_ARMOR).getValue() + "❈ Defense"));
               } else if ((Integer)main.actionBar.get(p) == 2) {
                  p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4§l" + (int)p.getHealth() + "❤ §r§8[§6§lᚦ§r §7" + Hrac.getLevel(2, p) + " §7" + Hrac.getXP(2, p) + "§8/§7" + Hrac.getNextLevelXP(2, p) + "§8] §a" + p.getAttribute(Attribute.GENERIC_ARMOR).getValue() + "❈ Defense"));
               }
            } else {
               p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4§l" + (int)p.getHealth() + "❤ §a" + p.getAttribute(Attribute.GENERIC_ARMOR).getValue() + "❈ Defense"));
            }

         }
      }, 20L, 20L);
   }

   public static void sendTablist(Player p) {
      p.setPlayerListHeaderFooter("§6§lPoustevnici.cz", "§9TPS: §b" + Lag.getTPS());
   }

   public static double getNextLevelXP(int CLASS, Player player) {
      FileConfiguration config = Classes.getCustomConfig();
      double nextLevelXP = 0.0D;
      switch(CLASS) {
      case 0:
         nextLevelXP = (double)(25 * getLevel(0, player) * (1 + getLevel(0, player)));
         return nextLevelXP;
      case 1:
         nextLevelXP = (double)(5 * getLevel(1, player) * (1 + getLevel(1, player)));
         return nextLevelXP;
      case 2:
         nextLevelXP = (double)(3 * getLevel(2, player) * (1 + getLevel(2, player)));
         return nextLevelXP;
      default:
         return 0.0D;
      }
   }

   public static void setLevel(int CLASS, Player player, int level) {
      FileConfiguration config = Players.getCustomConfig();
      switch(CLASS) {
      case 0:
         config.set(player.getName() + ".miner.level", level);

         try {
            Players.save();
         } catch (IOException var7) {
            var7.printStackTrace();
         }
         break;
      case 1:
         config.set(player.getName() + ".hunter.level", level);

         try {
            Players.save();
         } catch (IOException var6) {
            var6.printStackTrace();
         }
         break;
      case 2:
         config.set(player.getName() + ".lumberjack.level", level);

         try {
            Players.save();
         } catch (IOException var5) {
            var5.printStackTrace();
         }
      }

   }

   public static void setXP(int CLASS, Player player, double xp) {
      String stringXP = (new DecimalFormat("#.00")).format(xp);
      FileConfiguration config = Players.getCustomConfig();
      switch(CLASS) {
      case 0:
         config.set(player.getName() + ".miner.xp", Double.parseDouble(stringXP.replace(",", ".")));

         try {
            Players.save();
         } catch (IOException var9) {
            var9.printStackTrace();
         }
         break;
      case 1:
         config.set(player.getName() + ".hunter.xp", Double.parseDouble(stringXP.replace(",", ".")));

         try {
            Players.save();
         } catch (IOException var8) {
            var8.printStackTrace();
         }
         break;
      case 2:
         config.set(player.getName() + ".lumberjack.xp", Double.parseDouble(stringXP.replace(",", ".")));

         try {
            Players.save();
         } catch (IOException var7) {
            var7.printStackTrace();
         }
      }

   }

   public static String replaceLast(String string, String toReplace, String replacement) {
      int pos = string.lastIndexOf(toReplace);
      return pos > -1 ? string.substring(0, pos) + replacement + string.substring(pos + toReplace.length()) : string;
   }

   public static boolean blockPlacedByPlayer(Block block) {
      FileConfiguration config = PlayerBlocks.getCustomConfig();
      return config.contains("chunk." + block.getChunk().getX() + "-" + block.getChunk().getZ() + "." + block.getX() + "-" + block.getY() + "-" + block.getZ());
   }

   public static void logBlockPlacedByPlayer(Player player, Block block) {
      FileConfiguration config = PlayerBlocks.getCustomConfig();
      config.set("chunk." + block.getChunk().getX() + "-" + block.getChunk().getZ() + "." + block.getX() + "-" + block.getY() + "-" + block.getZ(), player.getName());
      PlayerBlocks.save();
   }
}
