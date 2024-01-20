package de.zoeyvid.stacker.Listener;

import de.zoeyvid.stacker.main;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();
    if (main.getVelocity().containsKey(player)) {
      player.setVelocity(main.getVelocity().get(player));
      main.getThrown().add(player);
      main.getVelocity().remove(player);
    }
    if (main.getVelocity().containsKey(player) && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR && player.getFallDistance() == 0) {
      main.getVelocity().remove(player);
    }
    if (main.getThrown().contains(player) && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR && player.getFallDistance() == 0) {
      main.getThrown().remove(player);
    }
  }
}
