package cz.teez0.main.Events;

import cz.teez0.main.main;
import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

public class PlayerEnterBed implements Listener {
   @EventHandler
   public void onPlayerEnterBed(PlayerBedEnterEvent e) {
      if (e.getBedEnterResult() == BedEnterResult.OK) {
         ((main)main.getPlugin(main.class)).getServer().broadcastMessage(e.getPlayer().getName() + " §6šel spát. Sladké sny.");
         int sleepingPlayers = 1;
         int playerCount = 0;

         for(Iterator i$ = ((main)main.getPlugin(main.class)).getServer().getOnlinePlayers().iterator(); i$.hasNext(); ++playerCount) {
            Player player = (Player)i$.next();
            if (player.isSleeping()) {
               ++sleepingPlayers;
            }
         }

         if ((double)sleepingPlayers >= (double)playerCount / 2.5D) {
            e.getPlayer().getWorld().setTime(0L);
         }
      }

   }
}
