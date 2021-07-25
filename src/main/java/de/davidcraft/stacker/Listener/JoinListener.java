package de.davidcraft.stacker.Listener;

import de.davidcraft.stacker.loadConfig;
import de.davidcraft.stacker.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!loadConfig.saveState()) {
            main.getDisabled().remove(player);
            main.getStackmode().remove(player);
        }
        if (loadConfig.defaultImmune()) main.getDisabled().add(player);
        if (loadConfig.defaultON()) main.getStackmode().add(player);
    }
}
