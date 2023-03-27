package de.zoeyvid.stacker.Listener;

import de.zoeyvid.stacker.loadConfig;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteractListener(PlayerInteractEvent event) {
        if(!loadConfig.throwable()) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR) && !(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player player = event.getPlayer();
        if(!(player.isSneaking())) return;
        Entity passenger = player.getPassengers().get(0);
        passenger.eject();
        passenger.setVelocity(new Vector(0,1.5,0));
    }

}
