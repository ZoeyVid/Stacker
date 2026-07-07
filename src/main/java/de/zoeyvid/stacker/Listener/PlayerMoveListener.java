package de.zoeyvid.stacker.Listener;

import de.zoeyvid.stacker.main;
import java.util.UUID;
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
    UUID id = player.getUniqueId();
    if (main.getVelocity().containsKey(id)) {
      player.setVelocity(main.getVelocity().get(id));
      main.getThrown().add(id);
      main.getVelocity().remove(id);
    }
    if (main.getThrown().contains(id) && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR && player.getFallDistance() == 0) {
      main.getThrown().remove(id);
    }
  }
}
