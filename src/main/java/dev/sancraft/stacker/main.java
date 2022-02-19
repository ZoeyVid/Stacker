package dev.sancraft.stacker;

import dev.sancraft.stacker.Listener.EntityInteractListener;
import dev.sancraft.stacker.Listener.JoinListener;
import dev.sancraft.stacker.Listener.QuitListener;
import dev.sancraft.stacker.Listener.ShiftListener;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public final class main extends JavaPlugin {

    private static final String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Sta" + ChatColor.DARK_AQUA + "cker" + ChatColor.GRAY + "] ";
    private static final ArrayList stackmode = new ArrayList<Player>();
    private static final ArrayList disabled = new ArrayList<Player>();
    private FileConfiguration cfg;
    private FileConfiguration lang;
    private final int langVersion = 2;

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
        loadConfig.readConfig();
        commandRegistration();
        listenerRegistration();
        loadLanguages();
        loadLanguage.readLanguage();
        updatePlugin();
        updateLanguage();
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
            Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + "Create and loaded config file!");
        }
    }

    private void loadLanguages() {
        File langDir = new File("plugins/Stacker");
        if (!langDir.exists()) {
            langDir.mkdirs();
        }
        if ((new File("plugins/Stacker/language/" + loadConfig.language() + ".yml")).exists()) {
            lang = getConfig();
            lang.options().copyDefaults(true);
            Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + "Language file loaded! (" + loadConfig.language() + ")");
        } else {
            try {
                FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/SanCraftDev/Stacker/master/languages/" + loadConfig.language() + ".yml"), new File("plugins/Stacker/language/" + loadConfig.language() + ".yml"));
            } catch (Exception e) {
                try {
                    FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/SanCraftDev/Stacker/master/languages/en.yml"), new File("plugins/Stacker/language/" + loadConfig.language() + ".yml"));
                } catch (Exception e1) {
                    Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.RED + "Error while downloading language file!");
                    Bukkit.getServer().getPluginManager().disablePlugin(this);
                }
            }
        }
    }

    private void updatePlugin() {
        if (loadConfig.autoUpdate()) {
            try {
                FileUtils.copyURLToFile(new URL("https://ci.sancraft.dev/job/Stacker/lastSuccessfulBuild/artifact/target/stacker.jar"), new File("plugins/stacker.jar"));
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.RED + "Error while updating the plugin!");
            }
        }
    }

    private void updateLanguage() {
        if (loadConfig.autoUpdateLanguage() && loadLanguage.getVersion() != langVersion) {
            try {
                FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/SanCraftDev/Stacker/master/languages/" + loadConfig.language() + ".yml"), new File("plugins/Stacker/language/" + loadConfig.language() + ".yml"));
            } catch (Exception e) {
                try {
                    FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/SanCraftDev/Stacker/master/languages/en.yml"), new File("plugins/Stacker/language/" + loadConfig.language() + ".yml"));
                } catch (Exception e1) {
                    Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.RED + "Error while downloading language file!");
                    Bukkit.getServer().getPluginManager().disablePlugin(this);
                }
            }
            Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + "Language file updated! (" + loadConfig.language() + ", " + loadLanguage.getVersion() + " -> " + langVersion + ")");
            loadLanguages();
            loadLanguage.readLanguage();
        }
    }

    private void commandRegistration() {
        getCommand("stacker").setExecutor(new StackerCommand());
    }
}
