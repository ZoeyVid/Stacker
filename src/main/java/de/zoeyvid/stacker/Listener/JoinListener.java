package de.zoeyvid.stacker.Listener;

import de.zoeyvid.stacker.loadConfig;
import de.zoeyvid.stacker.main;
import java.util.UUID;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    UUID player = event.getPlayer().getUniqueId();
    if (!loadConfig.saveState()) {
      main.getDisabled().remove(player);
      main.getStackmode().remove(player);
    }
    if (loadConfig.defaultImmune()) main.getDisabled().add(player);
    if (loadConfig.defaultON()) main.getStackmode().add(player);
  }
}
