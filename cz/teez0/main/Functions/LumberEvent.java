package cz.teez0.main.Functions;

import cz.teez0.main.main;
import cz.teez0.main.Files.Players;
import java.io.IOException;
import java.util.Random;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LumberEvent {
   public static void doLumberLevelBonus(Player player, Block block) {
      int bonusLogChance = Hrac.getLevel(2, player) * 10;
      if (bonusLogChance > 100) {
         bonusLogChance = 100;
      }

      int randomChance = (new Random()).nextInt(100);
      if (randomChance <= bonusLogChance) {
         block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(block.getType(), 1));
      }

   }

   public static void doLumberLevel2Bonus(Player player, final Block block, final Material sapling) {
      if (Hrac.getNextLevel(2, player) >= 11.0D) {
         Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(main.class), new Runnable() {
            public void run() {
               if (block.getRelative(BlockFace.DOWN).getType() == Material.GRASS_BLOCK) {
                  block.setType(sapling, true);
               } else if (block.getRelative(BlockFace.DOWN).getType() == Material.DIRT) {
                  block.setType(sapling, true);
               }

            }
         }, 5L);
      }

   }

   public static void doLumberLevel4Bonus(Player player, Block block) {
      int maxHeightY = block.getY() + 10;

      for(int i = block.getY(); i <= maxHeightY; ++i) {
         Block nBlock = block.getWorld().getBlockAt(block.getX(), i, block.getZ());
         if (nBlock.getType() == Material.OAK_LOG || nBlock.getType() == Material.ACACIA_LOG || nBlock.getType() == Material.BIRCH_LOG || nBlock.getType() == Material.DARK_OAK_LOG || nBlock.getType() == Material.JUNGLE_LOG || nBlock.getType() == Material.SPRUCE_LOG) {
            nBlock.getWorld().dropItemNaturally(nBlock.getLocation(), new ItemStack(nBlock.getType(), 1));
            nBlock.setType(Material.AIR);
         }
      }

   }

   public static void doLumberEvent(Player player, double xp) {
      FileConfiguration config = Players.getCustomConfig();
      main.actionBar.put(player, 2);
      if (Hrac.getLevel(2, player) == 50 && !config.contains(player.getName() + ".lumberjack.max")) {
         player.sendMessage("§6§lCONGRATULATIONS! §r§6Lumber §9level §2§lMAX §r§9reached!\n§r§6§lNEW ABILITY: §r§9Instantly chop down trees");
         config.set(player.getName() + ".lumberjack.max", true);

         try {
            Players.save();
         } catch (IOException var8) {
            var8.printStackTrace();
         }
      }

      double playerXP = 0.0D;
      String message = "";
      playerXP = Hrac.getXP(2, player);
      playerXP += xp * 1.05D;
      if (!config.contains(player.getName() + ".lumberjack.max")) {
         Hrac.setXP(2, player, playerXP);
      }

      int bonusLogChance = Hrac.getLevel(2, player) * 10;
      if (bonusLogChance > 100) {
         bonusLogChance = 100;
      }

      if (Hrac.getNextLevel(2, player) <= 0.0D) {
         Hrac.setLevel(2, player, Hrac.getLevel(2, player) + 1);
         Hrac.setXP(2, player, 0.0D);
         message = "§6Lumberjack §9§lLevel UP!\n";
         if (Hrac.getLevel(2, player) < 11) {
            message = message + "§9You now have §l" + bonusLogChance + "% §9chance of getting extra log from logging!\n";
         }

         if (Hrac.getLevel(2, player) == 11) {
            message = message + "§6§lNEW ABILITY: §r§6Logging trees will now automatically plant saplings\n";
         }

         message = Hrac.replaceLast(message, "\n", "");
         player.sendMessage(message);
      } else {
         player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4§l" + (int)player.getHealth() + "❤ §r§8[§6§lᚦ§r §7" + Hrac.getLevel(2, player) + " §7" + Hrac.getXP(2, player) + "§8/§7" + Hrac.getNextLevelXP(2, player) + "§8]  §a" + player.getAttribute(Attribute.GENERIC_ARMOR).getValue() + "❈ Defense"));
      }
   }
}
