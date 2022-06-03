package cz.teez0.main.Functions;

import cz.teez0.main.main;
import cz.teez0.main.Files.Players;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class MinerEvent {
   public static boolean doMinerLevel2Bonus(Player player, Block block) {
      int minerPlayerLevel = Hrac.getLevel(0, player);
      if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
         return false;
      } else {
         if (minerPlayerLevel >= 13) {
            double bonusOreChance = (double)((minerPlayerLevel - 13) * 4);
            if (bonusOreChance > 100.0D) {
               bonusOreChance = 100.0D;
            }

            int randomChance = (new Random()).nextInt(100);
            if ((double)randomChance <= bonusOreChance) {
               ItemStack item = player.getInventory().getItemInMainHand();

               Iterator result;
               ItemStack stack;
               for(result = block.getDrops(player.getInventory().getItemInMainHand()).iterator(); result.hasNext(); item = stack) {
                  stack = (ItemStack)result.next();
               }

               result = null;
               Iterator iter = ((main)main.getPlugin(main.class)).getServer().recipeIterator();

               while(iter.hasNext()) {
                  Recipe recipe = (Recipe)iter.next();
                  if (recipe instanceof FurnaceRecipe && ((FurnaceRecipe)recipe).getInput().getType() == item.getType()) {
                     ItemStack result = recipe.getResult();
                     player.getWorld().dropItemNaturally(block.getLocation(), result);
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   public static boolean doMinerLevel3Bonus(Player player, Block block) {
      int minerPlayerLevel = Hrac.getLevel(0, player);
      if (minerPlayerLevel >= 26) {
         double explodeOreChance = (double)((minerPlayerLevel - 25) * 4);
         if (explodeOreChance > 10.0D) {
            explodeOreChance = 10.0D;
         }

         int randomChance = (new Random()).nextInt(100);
         if ((double)randomChance <= explodeOreChance) {
            Material blockMaterial = block.getType();
            int blockCount = 0;
            double[] distance = new double[0];
            Iterator i$ = getNearbyBlocks(block.getLocation(), 5).iterator();

            while(true) {
               while(true) {
                  Block blck;
                  do {
                     do {
                        if (!i$.hasNext()) {
                           return false;
                        }

                        blck = (Block)i$.next();
                     } while(blck.getType() != blockMaterial);
                  } while(4.0D >= Math.sqrt(Math.pow((double)(player.getLocation().getBlockX() - blck.getLocation().getBlockX()), 2.0D) + Math.pow((double)(player.getLocation().getBlockY() - blck.getLocation().getBlockY()), 2.0D) + Math.pow((double)(player.getLocation().getBlockZ() - blck.getLocation().getBlockZ()), 2.0D)));

                  blck.breakNaturally(player.getInventory().getItemInMainHand());
                  ++blockCount;
                  if (blockCount > 4) {
                     return false;
                  }

                  if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                     doMinerLevelBonus(player, blck);
                  } else {
                     Iterator i$ = block.getDrops(player.getInventory().getItemInMainHand()).iterator();

                     while(i$.hasNext()) {
                        ItemStack stack = (ItemStack)i$.next();
                        doMinerLevel2Bonus(player, block);
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   public static void doMinerLevel4Bonus(Player player, Block block) {
      if (Hrac.getLevel(0, player) == 50 && block.getType() == Material.SPAWNER) {
         player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.SPAWNER, 1));
      }

   }

   public static List<Block> getNearbyBlocks(Location location, int radius) {
      List<Block> blocks = new ArrayList();

      for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; ++x) {
         for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; ++y) {
            for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; ++z) {
               if (location.getWorld().getBlockAt(x, y, z).getType() == location.getBlock().getType()) {
                  blocks.add(location.getWorld().getBlockAt(x, y, z));
               }
            }
         }
      }

      return blocks;
   }

   public static void doMinerLevelBonus(Player player, Block block) {
      int minerPlayerLevel = Hrac.getLevel(0, player);
      if (minerPlayerLevel >= 1) {
         int bonusOreChance = minerPlayerLevel * 4;
         if (bonusOreChance > 100) {
            bonusOreChance = 100;
         }

         int randomChance = (new Random()).nextInt(100);
         if (randomChance <= bonusOreChance) {
            Iterator i$ = block.getDrops(player.getInventory().getItemInMainHand()).iterator();

            while(i$.hasNext()) {
               ItemStack stack = (ItemStack)i$.next();
               player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(stack.getType(), 1));
            }
         }
      }

   }

   public static void doMinerEvent(Player player, double xp) {
      FileConfiguration config = Players.getCustomConfig();
      main.actionBar.put(player, 0);
      if (Hrac.getLevel(0, player) == 50 && !config.contains(player.getName() + ".miner.max")) {
         player.sendMessage("§6§lCONGRATULATIONS! §r§7Miner §9level §2§lMAX §r§9reached!\n§r§6§lNEW ABILITY: §r§9Mining spawners now drops them");
         config.set(player.getName() + ".miner.max", true);

         try {
            Players.save();
         } catch (IOException var11) {
            var11.printStackTrace();
         }
      }

      double playerXP = 0.0D;
      String message = "";
      playerXP = Hrac.getXP(0, player);
      playerXP += xp * 10.15D;
      if (!config.contains(player.getName() + ".miner.max")) {
         Hrac.setXP(0, player, playerXP);
      }

      int bonusOreChance = Hrac.getLevel(0, player) * 4;
      if (bonusOreChance > 100) {
         bonusOreChance = 100;
      }

      int smeltOreChance = (Hrac.getLevel(0, player) - 13) * 4;
      if (smeltOreChance > 100) {
         smeltOreChance = 100;
      }

      double explodeOreChance = (double)(Hrac.getLevel(0, player) - 25) * 0.4D;
      if (explodeOreChance > 10.0D) {
         explodeOreChance = 10.0D;
      }

      explodeOreChance = Double.parseDouble((new DecimalFormat("#.00")).format(explodeOreChance).replace(",", "."));
      if (Hrac.getNextLevel(0, player) <= 0.0D) {
         Hrac.setLevel(0, player, Hrac.getLevel(0, player) + 1);
         Hrac.setXP(0, player, 0.0D);
         message = "§7Miner §9§lLevel UP!\n";
         if (Hrac.getLevel(0, player) < 26) {
            message = message + "§9You now have §l" + bonusOreChance + "% §9chance of getting extra ore from mining!\n";
         }

         if (Hrac.getLevel(0, player) == 13) {
            message = message + "§6§lNEW ABILITY: §r§6Mining ores now has §l§94%§r§6 chance of instantly smelting them\n";
         }

         if (Hrac.getLevel(0, player) > 13 && Hrac.getLevel(0, player) <= 38) {
            message = message + "§9You now have §l" + smeltOreChance + "% §9chance of instantly smelting ores!\n";
         }

         if (Hrac.getLevel(0, player) == 26) {
            message = message + "§6§lNEW ABILITY: §r§6Mining ores now has §l§90.4%§r§6 chance of instantly mining 4 surrounding ores\n";
         }

         if (Hrac.getLevel(0, player) > 26 && Hrac.getLevel(0, player) <= 50) {
            message = message + "§9You now have §l" + explodeOreChance + "% §9chance of instantly mining 4 surrounding ores!\n";
         }

         message = Hrac.replaceLast(message, "\n", "");
         player.sendMessage(message);
      } else {
         player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4§l" + (int)player.getHealth() + "❤ §r§8[§8⛏ §7" + Hrac.getLevel(0, player) + " §7" + Hrac.getXP(0, player) + "§8/§7" + Hrac.getNextLevelXP(0, player) + "§8]  §a" + player.getAttribute(Attribute.GENERIC_ARMOR).getValue() + "❈ Defense"));
      }
   }
}
