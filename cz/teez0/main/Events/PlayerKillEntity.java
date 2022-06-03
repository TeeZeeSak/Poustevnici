package cz.teez0.main.Events;

import cz.teez0.main.main;
import cz.teez0.main.Functions.PlayerKillEntityEvent;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerKillEntity implements Listener {
   @EventHandler
   public void onPlayerKillEntity(EntityDeathEvent e) {
      Player player = e.getEntity().getKiller();
      LivingEntity entity = e.getEntity();
      if (player instanceof Player && (entity instanceof Animals || entity instanceof Monster && !(entity instanceof Player))) {
         PlayerKillEntityEvent.doPlayerKillEntityEvent(player, entity, e);
         main.actionBar.put(player, 1);
      }

   }
}
