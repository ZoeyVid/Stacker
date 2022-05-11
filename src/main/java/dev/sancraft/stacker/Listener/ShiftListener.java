package dev.sancraft.stacker.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ShiftListener implements Listener {

  @EventHandler
  public void onShift(PlayerToggleSneakEvent event) {
    Player player = event.getPlayer();
    if (!player.getPassengers().equals(null)) {
      player.eject();
    }
  }
}
