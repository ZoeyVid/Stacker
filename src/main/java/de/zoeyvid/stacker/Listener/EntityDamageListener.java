package de.zoeyvid.stacker.Listener;

import de.zoeyvid.stacker.loadConfig;
import de.zoeyvid.stacker.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamageListener implements Listener {

  @EventHandler
  public void onEntityDamage(EntityDamageEvent event) {
    if (loadConfig.throwfalldamage()) return;
    if (!(event.getEntity() instanceof Player)) return;
    Player player = (Player) event.getEntity();
    if (main.getThrown().contains(player) && event.getCause() == DamageCause.FALL) {
      event.setCancelled(true);
    }
  }
}
