package cz.teez0.main.Functions;

import cz.teez0.main.Files.MobEggs;
import cz.teez0.main.Files.Players;
import java.io.IOException;
import java.util.Random;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerKillEntityEvent {
   public static void level1Bonus(Player player, int extraxp, EntityDeathEvent e) {
      e.setDroppedExp(e.getDroppedExp() + extraxp);
   }

   public static boolean level2Bonus(Player player, int deflectChance) {
      int randomChance = (new Random()).nextInt(100);
      return deflectChance <= randomChance;
   }

   public static void level3Bonus(Player player, LivingEntity entity, double chance) {
      FileConfiguration config = MobEggs.getCustomConfig();
      double randomChance = (new Random()).nextDouble();
      if (chance >= randomChance && config.contains("mob_eggs." + entity.getName().toLowerCase())) {
         String SpawnEgg = config.getString("mob_eggs." + entity.getName().toLowerCase());
         Material SpawnEggMaterial = Material.matchMaterial(SpawnEgg);
         entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(SpawnEggMaterial, 1));
      }

   }

   public static void doPlayerKillEntityEvent(Player player, LivingEntity entity, EntityDeathEvent event) {
      FileConfiguration config = Players.getCustomConfig();
      if (Hrac.getLevel(1, player) == 50 && !config.contains(player.getName() + ".hunter.max")) {
         player.sendMessage("§6§lCONGRATULATIONS! §r§4Hunter §9level §2§lMAX §r§9reached!\n§r§6§lNEW ABILITY: §r§9Mining spawners now drops them");
         config.set(player.getName() + ".hunter.max", true);

         try {
            Players.save();
         } catch (IOException var14) {
            var14.printStackTrace();
         }
      }

      double xp = 0.0D;
      if (entity instanceof Animals) {
         xp = 2.0D;
      }

      if (entity instanceof Monster) {
         xp = 5.0D;
      }

      double playerXP = 0.0D;
      String message = "";
      playerXP = Hrac.getXP(1, player);
      playerXP += xp * 2.5D;
      if (!config.contains(player.getName() + ".hunter.max")) {
         Hrac.setXP(1, player, playerXP);
      }

      if (Hrac.getNextLevel(1, player) <= 0.0D) {
         Hrac.setLevel(1, player, Hrac.getLevel(1, player) + 1);
         Hrac.setXP(1, player, 0.0D);
         message = "§cHunter §9§lLevel UP!\n";
         if (Hrac.getLevel(1, player) == 1) {
            message = message + "§9You now get §6§l+1 §r§9experience from killing monsters! (§6§l+0 §r§9from animals§r§9)\n";
         }

         if (Hrac.getLevel(1, player) == 5) {
            message = message + "§9You now get §6§l+1 §r§9experience from killing monsters! (§6§l+1 §r§9from animals§r§9)\n";
         }

         if (Hrac.getLevel(1, player) == 10) {
            message = message + "§9You now get §6§l+2 §r§9experience from killing monsters! (§6§l+1 §r§9from animals§r§9)\n";
         }

         if (Hrac.getLevel(1, player) == 15) {
            message = message + "§9You now get §6§l+2 §r§9experience from killing monsters! (§6§l+2 §r§9from animals§r§9)\n";
         }

         if (Hrac.getLevel(1, player) == 20) {
            message = message + "§9You now get §6§l+3 §r§9experience from killing monsters! (§6§l+2 §r§9from animals§r§9)\n";
         }

         if (Hrac.getLevel(1, player) == 25) {
            message = message + "§9You now get §6§l+3 §r§9experience from killing monsters! (§6§l+3 §r§9from animals§r§9)\n";
         }

         if (Hrac.getLevel(1, player) == 26) {
            message = message + "§6§lNEW ABILITY: §r§6Now you have §l§95%§r§6 chance of deflecting arrows\n";
         }

         if (Hrac.getLevel(1, player) == 32) {
            message = message + "§6Now you have §l§98%§r§6 chance of deflecting arrows\n";
         }

         if (Hrac.getLevel(1, player) == 38) {
            message = message + "§6Now you have §l§912%§r§6 chance of deflecting arrows\n";
         }

         if (Hrac.getLevel(1, player) == 44) {
            message = message + "§6Now you have §l§920%§r§6 chance of deflecting arrows\n";
         }

         if (Hrac.getLevel(1, player) == 50) {
            message = message + "§6§lNEW ABILITY: §r§6Now you have 0.075% chance of getting mob egg\n";
         }

         message = Hrac.replaceLast(message, "\n", "");
         player.sendMessage(message);
      } else {
         int playerLevel = Hrac.getLevel(1, player);
         int extraXP = 0;
         int deflectChance = 0;
         double mobeggChance = 0.0D;
         if (playerLevel >= 1 && entity instanceof Monster) {
            extraXP = 1;
         }

         if (playerLevel >= 5) {
            extraXP = 1;
         }

         if (playerLevel >= 10) {
            if (entity instanceof Monster) {
               extraXP = 2;
            } else if (entity instanceof Animals) {
               extraXP = 1;
            }
         }

         if (playerLevel >= 15) {
            extraXP = 2;
         }

         if (playerLevel >= 20) {
            if (entity instanceof Monster) {
               extraXP = 3;
            } else if (entity instanceof Animals) {
               extraXP = 2;
            }
         }

         if (playerLevel >= 25) {
            extraXP = 3;
         }

         if (playerLevel >= 26) {
            deflectChance = 5;
         }

         if (playerLevel >= 32) {
            deflectChance = 8;
         }

         if (playerLevel >= 38) {
            deflectChance = 12;
         }

         if (playerLevel >= 44) {
            deflectChance = 20;
         }

         if (playerLevel >= 50) {
            mobeggChance = 0.0075D;
         }

         level3Bonus(player, entity, mobeggChance);
         level2Bonus(player, deflectChance);
         level1Bonus(player, extraXP, event);
         player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4§l" + (int)player.getHealth() + "❤ §r§8[§c⚔ §7" + Hrac.getLevel(1, player) + " §7" + Hrac.getXP(1, player) + "§8/§7" + Hrac.getNextLevelXP(1, player) + "§8]  §a" + player.getAttribute(Attribute.GENERIC_ARMOR).getValue() + "❈ Defense"));
      }
   }
}
