package cz.teez0.main.Events;

import cz.teez0.main.Functions.Hrac;
import cz.teez0.main.Functions.LumberEvent;
import cz.teez0.main.Functions.MinerEvent;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerBreak implements Listener {
   @EventHandler(
      priority = EventPriority.LOWEST
   )
   public void onPlayerBreak(BlockBreakEvent e) {
      Player player = e.getPlayer();
      Material block = e.getBlock().getType();
      if (!Hrac.blockPlacedByPlayer(e.getBlock())) {
         Iterator i$;
         ItemStack stack;
         switch(block) {
         case STONE:
            MinerEvent.doMinerEvent(player, 1.2D);
            break;
         case ANDESITE:
            MinerEvent.doMinerEvent(player, 2.0D);
            break;
         case COAL_ORE:
            MinerEvent.doMinerEvent(player, 1.5D);
            MinerEvent.doMinerLevelBonus(player, e.getBlock());
            MinerEvent.doMinerLevel3Bonus(player, e.getBlock());
            break;
         case DARK_PRISMARINE:
            MinerEvent.doMinerEvent(player, 10.0D);
            break;
         case DIORITE:
            MinerEvent.doMinerEvent(player, 2.0D);
            break;
         case END_STONE:
            MinerEvent.doMinerEvent(player, 1.4D);
            break;
         case GRANITE:
            MinerEvent.doMinerEvent(player, 2.0D);
            break;
         case MOSSY_COBBLESTONE:
            MinerEvent.doMinerEvent(player, 2.5D);
            break;
         case NETHER_BRICK:
            MinerEvent.doMinerEvent(player, 3.0D);
            break;
         case NETHERRACK:
            MinerEvent.doMinerEvent(player, 1.0D);
            break;
         case NETHER_QUARTZ_ORE:
            MinerEvent.doMinerEvent(player, 5.0D);
            boolean doLevel2Bonus = MinerEvent.doMinerLevel2Bonus(player, e.getBlock());
            if (doLevel2Bonus) {
               e.setDropItems(false);
               i$ = e.getBlock().getDrops(player.getInventory().getItemInMainHand()).iterator();

               while(i$.hasNext()) {
                  stack = (ItemStack)i$.next();
                  player.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.QUARTZ, stack.getAmount()));
               }

               return;
            } else {
               if (MinerEvent.doMinerLevel3Bonus(player, e.getBlock())) {
                  MinerEvent.doMinerLevelBonus(player, e.getBlock());
                  e.setDropItems(false);
               }
               break;
            }
         case PRISMARINE:
            MinerEvent.doMinerEvent(player, 8.0D);
            break;
         case PRISMARINE_BRICKS:
            MinerEvent.doMinerEvent(player, 5.0D);
            break;
         case RED_SANDSTONE:
            MinerEvent.doMinerEvent(player, 5.0D);
            break;
         case SANDSTONE:
            MinerEvent.doMinerEvent(player, 4.0D);
            break;
         case SPAWNER:
            MinerEvent.doMinerEvent(player, 25.0D);
            MinerEvent.doMinerLevel4Bonus(player, e.getBlock());
            break;
         case TERRACOTTA:
            MinerEvent.doMinerEvent(player, 3.0D);
            break;
         case IRON_ORE:
            MinerEvent.doMinerEvent(player, 2.2D);
            if (MinerEvent.doMinerLevel2Bonus(player, e.getBlock())) {
               e.setDropItems(false);
               i$ = e.getBlock().getDrops(player.getInventory().getItemInMainHand()).iterator();

               while(i$.hasNext()) {
                  stack = (ItemStack)i$.next();
                  player.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, stack.getAmount()));
               }
            } else {
               MinerEvent.doMinerLevelBonus(player, e.getBlock());
            }

            MinerEvent.doMinerLevel3Bonus(player, e.getBlock());
            break;
         case LAPIS_ORE:
            MinerEvent.doMinerEvent(player, 7.0D);
            MinerEvent.doMinerLevelBonus(player, e.getBlock());
            MinerEvent.doMinerLevel3Bonus(player, e.getBlock());
            break;
         case DIAMOND_ORE:
            MinerEvent.doMinerEvent(player, 20.0D);
            MinerEvent.doMinerLevelBonus(player, e.getBlock());
            MinerEvent.doMinerLevel3Bonus(player, e.getBlock());
            break;
         case EMERALD_ORE:
            MinerEvent.doMinerEvent(player, 25.0D);
            MinerEvent.doMinerLevelBonus(player, e.getBlock());
            MinerEvent.doMinerLevel3Bonus(player, e.getBlock());
            break;
         case GOLD_ORE:
            MinerEvent.doMinerEvent(player, 3.0D);
            if (MinerEvent.doMinerLevel2Bonus(player, e.getBlock()) && !player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
               e.setDropItems(false);
               i$ = e.getBlock().getDrops(player.getInventory().getItemInMainHand()).iterator();

               while(i$.hasNext()) {
                  stack = (ItemStack)i$.next();
                  player.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, stack.getAmount()));
               }
            } else {
               MinerEvent.doMinerLevelBonus(player, e.getBlock());
            }

            MinerEvent.doMinerLevel3Bonus(player, e.getBlock());
            break;
         case REDSTONE_ORE:
            MinerEvent.doMinerEvent(player, 9.0D);
            MinerEvent.doMinerLevelBonus(player, e.getBlock());
            MinerEvent.doMinerLevel3Bonus(player, e.getBlock());
            break;
         case OBSIDIAN:
            MinerEvent.doMinerEvent(player, 10.0D);
            MinerEvent.doMinerLevel3Bonus(player, e.getBlock());
            break;
         case OAK_LOG:
            LumberEvent.doLumberEvent(player, 3.0D);
            LumberEvent.doLumberLevelBonus(player, e.getBlock());
            LumberEvent.doLumberLevel2Bonus(player, e.getBlock(), Material.OAK_SAPLING);
            break;
         case SPRUCE_LOG:
            LumberEvent.doLumberEvent(player, 2.5D);
            LumberEvent.doLumberLevelBonus(player, e.getBlock());
            LumberEvent.doLumberLevel2Bonus(player, e.getBlock(), Material.SPRUCE_SAPLING);
            break;
         case BIRCH_LOG:
            LumberEvent.doLumberEvent(player, 3.0D);
            LumberEvent.doLumberLevelBonus(player, e.getBlock());
            LumberEvent.doLumberLevel2Bonus(player, e.getBlock(), Material.BIRCH_SAPLING);
            break;
         case JUNGLE_LOG:
            LumberEvent.doLumberEvent(player, 2.0D);
            LumberEvent.doLumberLevelBonus(player, e.getBlock());
            LumberEvent.doLumberLevel2Bonus(player, e.getBlock(), Material.JUNGLE_SAPLING);
            break;
         case ACACIA_LOG:
            LumberEvent.doLumberEvent(player, 3.5D);
            LumberEvent.doLumberLevelBonus(player, e.getBlock());
            LumberEvent.doLumberLevel2Bonus(player, e.getBlock(), Material.ACACIA_SAPLING);
            break;
         case DARK_OAK_LOG:
            LumberEvent.doLumberEvent(player, 1.0D);
            LumberEvent.doLumberLevelBonus(player, e.getBlock());
            LumberEvent.doLumberLevel2Bonus(player, e.getBlock(), Material.DARK_OAK_SAPLING);
         }
      }

   }
}
