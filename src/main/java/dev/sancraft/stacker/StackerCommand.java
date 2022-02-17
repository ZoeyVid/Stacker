package dev.sancraft.stacker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StackerCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_RED + "This Command can only execute as a player!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            if (player.hasPermission("stacker.use.self")) {
                if (main.getStackmode().contains(player)) {
                    main.getStackmode().remove(player);
                    player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveStackmode());
                    return true;
                } else {
                    main.getStackmode().add(player);
                    player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinStackmode());
                    return true;
                }
            } else {
                player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.use.self"));
                return true;
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("toggle")) {
                if (player.hasPermission("stacker.use.self")) {
                    if (main.getStackmode().contains(player)) {
                        main.getStackmode().remove(player);
                        player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveStackmode());
                    } else {
                        main.getStackmode().add(player);
                        player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinStackmode());
                    }
                } else {
                    player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.use.self"));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("help")) {
                if (player.hasPermission("stacker.help")) {
                    player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.helpTitle());
                    player.sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker " + ChatColor.WHITE + loadLanguage.helpNormal());
                    player.sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker toggle/enable/disable <player>" + ChatColor.WHITE + loadLanguage.helpToggle());
                    player.sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker immune <player>" + ChatColor.WHITE + loadLanguage.helpImmune());
                    player.sendMessage(main.getPrefix() + ChatColor.YELLOW + "/stacker help" + ChatColor.WHITE + loadLanguage.helpHelp());
                } else {
                    player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.help"));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("immune")) {
                if (player.hasPermission("stacker.immune.self")) {
                    if (main.getDisabled().contains(player)) {
                        main.getDisabled().remove(player);
                        player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveImmune());
                    } else {
                        main.getDisabled().add(player);
                        player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinImmune());
                    }
                } else {
                    player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.immune.self"));
                }
                return true;
            }
        } else {
            if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("toggle")) {
                if (player.hasPermission("stacker.use.other")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target == null) {
                        player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.errorPlayerOnline());
                    } else {
                        if (main.getStackmode().contains(target)) {
                            main.getStackmode().remove(target);
                            player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveStackmodeOther().replace("%player%", target.getName()));
                            if (loadConfig.sendMessage()) {
                                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveStackmodeSelf());
                            }
                        } else {
                            main.getStackmode().add(target);
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
            } else if (args[0].equalsIgnoreCase("immune")) {
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target == null) {
                    player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.errorPlayerOnline());
                } else {
                    if (player.hasPermission("stacker.immune.other")) {
                        if (main.getDisabled().contains(target)) {
                            main.getDisabled().remove(target);
                            player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveImmuneOther().replace("%player%", target.getName()));
                            if (loadConfig.sendMessage()) {
                                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.leaveImmuneSelf());
                            }
                        } else {
                            main.getDisabled().add(target);
                            player.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinImmuneOther().replace("%player%", target.getName()));
                            if (loadConfig.sendMessage()) {
                                target.sendMessage(main.getPrefix() + ChatColor.WHITE + loadLanguage.joinImmuneSelf());
                            }
                        }
                    } else {
                        player.sendMessage(main.getPrefix() + ChatColor.DARK_RED + loadLanguage.permissionError().replace("%permission%", "stacker.immune.other"));
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("toggle");
            arguments.add("enable");
            arguments.add("disable");
            arguments.add("immune");
            arguments.add("help");
            return arguments;
        } else if (args.length == 2) {
            List<String> players = new ArrayList<>();
            Player[] player = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(player);
            for (int i = 0; i < player.length; i++) {
                players.add(player[i].getName());
            }
            return players;
        }
        return null;
    }
}
