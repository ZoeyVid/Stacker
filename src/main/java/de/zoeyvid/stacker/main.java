package de.zoeyvid.stacker;

import de.zoeyvid.stacker.Listener.*;
import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class main extends JavaPlugin {
  private static final String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Stacker" + ChatColor.GRAY + "] ";
  private static final ArrayList<Player> stackmode = new ArrayList<>();
  private static final ArrayList<Player> disabled = new ArrayList<>();
  private static final ArrayList<Player> thrown = new ArrayList<>();
  private static final HashMap<Player, Vector> velocity = new HashMap<>();
  private final int langVersion = 2;
  private FileConfiguration cfg;
  private FileConfiguration lang;

  public static String getPrefix() {
    return prefix;
  }

  public static ArrayList<Player> getStackmode() {
    return stackmode;
  }

  public static ArrayList<Player> getDisabled() {
    return disabled;
  }

  public static ArrayList<Player> getThrown() {
    return thrown;
  }

  public static HashMap<Player, Vector> getVelocity() {
    return velocity;
  }

  private void listenerRegistration() {
    PluginManager pluginManager = Bukkit.getPluginManager();
    pluginManager.registerEvents(new JoinListener(), this);
    pluginManager.registerEvents(new QuitListener(), this);
    pluginManager.registerEvents(new ShiftListener(), this);
    pluginManager.registerEvents(new PlayerMoveListener(), this);
    pluginManager.registerEvents(new EntityDamageListener(), this);
    pluginManager.registerEvents(new PlayerInteractListener(), this);
    pluginManager.registerEvents(new EntityDamageByEntityListener(), this);
  }

  @Override
  public void onEnable() {
    Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_GREEN + "The Plugin was enabled!");
    loadConfig();
    commandRegistration();
    listenerRegistration();
    loadLanguages();
    updatePlugin();
    updateLanguage();
  }

  @Override
  public void onDisable() {
    Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_RED + "The Plugin was disabled!");
  }

  private void loadConfig() {
    File langDir = new File("plugins/Stacker");
    if (!langDir.exists()) {
      langDir.mkdirs();
    }
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
    loadConfig.readConfig();
  }

  private void loadLanguages() {
    File langDir = new File("plugins/Stacker/language");
    if (!langDir.exists()) {
      langDir.mkdirs();
    }
    if ((new File("plugins/Stacker/language/" + loadConfig.language() + ".yml")).exists()) {
      lang = getConfig();
      lang.options().copyDefaults(true);
      Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + "Language file loaded! (" + loadConfig.language() + ")");
    } else {
      try {
        Files.copy(new URI("https://raw.githubusercontent.com/ZoeyVid/Stacker/stable/languages/" + loadConfig.language() + ".yml").toURL().openStream(), new File("plugins/Stacker/language/" + loadConfig.language() + ".yml").toPath(), StandardCopyOption.REPLACE_EXISTING);
      } catch (Exception e) {
        try {
          Files.copy(new URI("https://raw.githubusercontent.com/ZoeyVid/Stacker/stable/languages/en.yml").toURL().openStream(), new File("plugins/Stacker/language/" + loadConfig.language() + ".yml").toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e1) {
          Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.RED + "Error while downloading language file!");
          Bukkit.getServer().getPluginManager().disablePlugin(this);
        }
      }
    }
    loadLanguage.readLanguage();
  }

  private void updateLanguage() {
    if (loadConfig.autoUpdateLanguage() && loadLanguage.getVersion() != langVersion) {
      try {
        Files.copy(new URI("https://raw.githubusercontent.com/ZoeyVid/Stacker/stable/languages/" + loadConfig.language() + ".yml").toURL().openStream(), new File("plugins/Stacker/language/" + loadConfig.language() + ".yml").toPath(), StandardCopyOption.REPLACE_EXISTING);
      } catch (Exception e) {
        try {
          Files.copy(new URI("https://raw.githubusercontent.com/ZoeyVid/Stacker/stable/languages/en.yml").toURL().openStream(), new File("plugins/Stacker/language/" + loadConfig.language() + ".yml").toPath(), StandardCopyOption.REPLACE_EXISTING);
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

  private void updatePlugin() {
    if (loadConfig.autoUpdate()) {
      try {
        Files.copy(new URI("https://github.com/ZoeyVid/Stacker/releases/latest/download/Stacker.jar").toURL().openStream(), new File("plugins/stacker.jar").toPath(), StandardCopyOption.REPLACE_EXISTING);
      } catch (Exception e) {
        Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.RED + "Error while updating the plugin!");
      }
    }
  }

  private void commandRegistration() {
    getCommand("stacker").setExecutor(new StackerCommand());
  }
}
