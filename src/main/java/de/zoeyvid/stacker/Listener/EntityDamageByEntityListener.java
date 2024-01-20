package de.zoeyvid.stacker.Listener;

import de.zoeyvid.stacker.loadConfig;
import de.zoeyvid.stacker.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

  @EventHandler
  public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    if (!(event.getDamager() instanceof Player)) return;
    Player player = (Player) event.getDamager();
    if (!player.isEmpty() && loadConfig.throwable()) {
      event.setCancelled(true);
      Player passenger = (Player) player.getPassengers().get(0);
      player.eject();
      main.getVelocity().put(passenger, player.getEyeLocation().getDirection().setY(1));
    }
  }
}
