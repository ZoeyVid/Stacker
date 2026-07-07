package de.zoeyvid.stacker.Listener;

import de.zoeyvid.stacker.loadConfig;
import de.zoeyvid.stacker.loadLanguage;
import de.zoeyvid.stacker.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractListener implements Listener {
  @EventHandler
  public void onEntityInteract(PlayerInteractEntityEvent event) {
    Player player = event.getPlayer();
    if (event.getHand() != EquipmentSlot.HAND || !(event.getRightClicked() instanceof Player) || !main.getStackmode().contains(player.getUniqueId())) return;
    Player target = (Player) event.getRightClicked();
    if (main.getDisabled().contains(target.getUniqueId())) {
      if (loadConfig.showMessage()) {
        player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.dontStack().replace("%player%", target.getName()));
      }
    } else if (player.getWorld().getPlayers().contains(target)) {
      player.addPassenger(target);
    }
  }
}
