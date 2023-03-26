package de.zoeyvid.stacker.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteractListener(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR) && !(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
    }

}
