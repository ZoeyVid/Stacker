package de.zoeyvid.stacker;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class StackerCommand implements CommandExecutor, TabCompleter {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("immune")) {
          Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_RED + "This command can only be executed as a player!");
          return true;
        }

        if (args[0].equalsIgnoreCase("toggle")) {
          Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_RED + "This command can only be executed as a player!");
          return true;
        }
      }

      if (args.length == 2) {
        if (args[0].equalsIgnoreCase("immune")) {
          Player target = Bukkit.getPlayerExact(args[1]);
          if (target == null) {
            Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.errorPlayerOnline());
          } else {
            if (main.getDisabled().contains(target.getUniqueId())) {
              main.getDisabled().remove(target.getUniqueId());
              Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveImmuneOther().replace("%player%", target.getName()));
              if (loadConfig.sendMessage()) {
                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveImmuneSelf());
              }
            } else {
              main.getDisabled().add(target.getUniqueId());
              Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinImmuneOther().replace("%player%", target.getName()));
              if (loadConfig.sendMessage()) {
                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinImmuneSelf());
              }
            }
          }
          return true;
        }

        if (args[0].equalsIgnoreCase("toggle")) {
          Player target = Bukkit.getPlayerExact(args[1]);
          if (target == null) {
            Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.errorPlayerOnline());
          } else {
            if (main.getStackmode().contains(target.getUniqueId())) {
              main.getStackmode().remove(target.getUniqueId());
              Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveStackmodeOther().replace("%player%", target.getName()));
              if (loadConfig.sendMessage()) {
                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveStackmodeSelf());
              }
            } else {
              main.getStackmode().add(target.getUniqueId());
              Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinStackmodeOther().replace("%player%", target.getName()));
              if (loadConfig.sendMessage()) {
                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinStackmodeSelf());
              }
            }
          }
          return true;
        }
      }

      Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.helpTitle());
      Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker [help]" + ChatColor.WHITE + loadLanguage.helpHelp());
      Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker immune [<player>]" + ChatColor.WHITE + loadLanguage.helpImmune());
      Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker toggle [<player>]" + ChatColor.WHITE + loadLanguage.helpToggle());
      return true;
    }

    Player player = (Player) sender;

    if (args.length == 1) {
      if (args[0].equalsIgnoreCase("immune")) {
        if (sender.hasPermission("stacker.immune.self")) {
          if (main.getDisabled().contains(player.getUniqueId())) {
            main.getDisabled().remove(player.getUniqueId());
            player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveImmune());
          } else {
            main.getDisabled().add(player.getUniqueId());
            player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinImmune());
          }
        } else {
          player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.immune.self"));
        }
        return true;
      }

      if (args[0].equalsIgnoreCase("toggle")) {
        if (sender.hasPermission("stacker.use.self")) {
          if (main.getStackmode().contains(player.getUniqueId())) {
            main.getStackmode().remove(player.getUniqueId());
            player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveStackmode());
          } else {
            main.getStackmode().add(player.getUniqueId());
            player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinStackmode());
          }
        } else {
          player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.use.self"));
        }
        return true;
      }
    }

    if (args.length == 2) {
      if (args[0].equalsIgnoreCase("immune")) {
        if (sender.hasPermission("stacker.immune.other")) {
          Player target = Bukkit.getPlayerExact(args[1]);
          if (target == null) {
            player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.errorPlayerOnline());
          } else {
            if (main.getDisabled().contains(target.getUniqueId())) {
              main.getDisabled().remove(target.getUniqueId());
              player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveImmuneOther().replace("%player%", target.getName()));
              if (loadConfig.sendMessage()) {
                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveImmuneSelf());
              }
            } else {
              main.getDisabled().add(target.getUniqueId());
              player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinImmuneOther().replace("%player%", target.getName()));
              if (loadConfig.sendMessage()) {
                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinImmuneSelf());
              }
            }
          }
        } else {
          player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.immune.other"));
        }
        return true;
      }

      if (args[0].equalsIgnoreCase("toggle")) {
        if (sender.hasPermission("stacker.use.other")) {
          Player target = Bukkit.getPlayerExact(args[1]);
          if (target == null) {
            player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.errorPlayerOnline());
          } else {
            if (main.getStackmode().contains(target.getUniqueId())) {
              main.getStackmode().remove(target.getUniqueId());
              player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveStackmodeOther().replace("%player%", target.getName()));
              if (loadConfig.sendMessage()) {
                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveStackmodeSelf());
              }
            } else {
              main.getStackmode().add(target.getUniqueId());
              player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinStackmodeOther().replace("%player%", target.getName()));
              if (loadConfig.sendMessage()) {
                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinStackmodeSelf());
              }
            }
          }
        } else {
          player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.use.other"));
        }
        return true;
      }
    }

    if (sender.hasPermission("stacker.help")) {
      player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.helpTitle());
      player.sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker [help]" + ChatColor.WHITE + loadLanguage.helpHelp());
      player.sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker immune [<player>]" + ChatColor.WHITE + loadLanguage.helpImmune());
      player.sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker toggle [<player>]" + ChatColor.WHITE + loadLanguage.helpToggle());
    } else {
      player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.help"));
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (args.length == 1) {
      List<String> arguments = new ArrayList<>();
      if (sender.hasPermission("stacker.immune.self")) arguments.add("immune");
      if (sender.hasPermission("stacker.use.self")) arguments.add("toggle");
      if (sender.hasPermission("stacker.help")) arguments.add("help");
      return arguments;
    }
    return null;
  }
}
