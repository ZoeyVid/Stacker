package de.zoeyvid.stacker.Listener;

import de.zoeyvid.stacker.loadConfig;
import de.zoeyvid.stacker.main;
import java.util.UUID;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    if (loadConfig.saveState()) return;
    UUID player = event.getPlayer().getUniqueId();
    main.getDisabled().remove(player);
    main.getStackmode().remove(player);
  }
}
