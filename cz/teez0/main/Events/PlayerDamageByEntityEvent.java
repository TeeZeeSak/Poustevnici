package cz.teez0.main.Events;

import cz.teez0.main.Functions.Hrac;
import cz.teez0.main.Functions.PlayerKillEntityEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageByEntityEvent implements Listener {
   @EventHandler
   public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {
      Entity damager = e.getDamager();
      Entity p = e.getEntity();
      if (damager instanceof Arrow && p instanceof Player) {
         Player player = ((Player)p).getPlayer();
         int playerLevel = Hrac.getLevel(1, player);
         int deflectChance = 0;
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
            deflectChance = 15;
         }

         if (playerLevel >= 50) {
            deflectChance = 20;
         }

         if (PlayerKillEntityEvent.level2Bonus(player, deflectChance)) {
            damager.setVelocity(damager.getVelocity().normalize().multiply(-1.0D));
         }
      }

   }
}
