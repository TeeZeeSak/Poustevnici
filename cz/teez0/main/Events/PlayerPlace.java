package cz.teez0.main.Events;

import cz.teez0.main.Functions.Hrac;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerPlace implements Listener {
   @EventHandler
   public void onPlaceEvent(BlockPlaceEvent e) {
      Hrac.logBlockPlacedByPlayer(e.getPlayer(), e.getBlockPlaced());
   }
}
