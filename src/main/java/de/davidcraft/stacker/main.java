package de.davidcraft.stacker;

import de.davidcraft.stacker.Listener.EntityInteractListener;
import de.davidcraft.stacker.Listener.JoinListener;
import de.davidcraft.stacker.Listener.QuitListener;
import de.davidcraft.stacker.Listener.ShiftListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public final class main extends JavaPlugin {

    private static final String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Sta" + ChatColor.DARK_AQUA + "cker" + ChatColor.GRAY + "] ";
    private static final ArrayList stackmode = new ArrayList<Player>();
    private static final ArrayList disabled = new ArrayList<Player>();
    private FileConfiguration cfg;

    public static String getPrefix() {
        return prefix;
    }

    public static ArrayList getStackmode() {
        return stackmode;
    }

    public static ArrayList getDisabled() {
        return disabled;
    }

    private void listenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new EntityInteractListener(), this);
        pluginManager.registerEvents(new ShiftListener(), this);
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_GREEN + "The Plugin was enabled!");
        loadConfig();
        loadConfig.ReadConfig();
        commandRegistration();
        listenerRegistration();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_RED + "The Plugin was disabled!");
    }

    private void loadConfig() {
        if ((new File("plugins/Stacker/config.yml")).exists()) {
            cfg = getConfig();
            cfg.options().copyDefaults(true);
            Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + "Config file loaded!");
        } else {
            saveDefaultConfig();
            cfg = getConfig();
            cfg.options().copyDefaults(true);
            Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + "Create and Loaded config file!");
        }
    }

    private void commandRegistration() {
        getCommand("stacker").setExecutor(new StackerCommand());
    }
}
