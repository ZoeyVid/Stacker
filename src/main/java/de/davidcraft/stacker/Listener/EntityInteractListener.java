package de.davidcraft.stacker.Listener;

import de.davidcraft.stacker.LoadConfig;
import de.davidcraft.stacker.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityInteractListener implements Listener {

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof Player && main.getStackmode().contains(player)) {
            Player target = (Player) event.getRightClicked();
            if (main.getDisabled().contains(target)) {
                if (LoadConfig.showMessage()) {
                    player.sendMessage(main.getPrefix() + ChatColor.WHITE + "The Player " + target.getName() + " can`t be stacked!");
                }
            } else {
                player.addPassenger(target);
            }
        }
    }
}
