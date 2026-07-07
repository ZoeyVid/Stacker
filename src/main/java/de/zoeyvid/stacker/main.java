package de.zoeyvid.stacker;

import de.zoeyvid.stacker.Listener.*;
import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class main extends JavaPlugin {
  private static final String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Stacker" + ChatColor.GRAY + "] ";
  private static final Set<UUID> stackmode = ConcurrentHashMap.newKeySet();
  private static final Set<UUID> disabled = ConcurrentHashMap.newKeySet();
  private static final Set<UUID> thrown = ConcurrentHashMap.newKeySet();
  private static final Map<UUID, Vector> velocity = new ConcurrentHashMap<>();
  private final int langVersion = 2;

  public static String getPrefix() {
    return prefix;
  }

  public static Set<UUID> getStackmode() {
    return stackmode;
  }

  public static Set<UUID> getDisabled() {
    return disabled;
  }

  public static Set<UUID> getThrown() {
    return thrown;
  }

  public static Map<UUID, Vector> getVelocity() {
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
    updateLanguage();
    updatePlugin();
  }

  @Override
  public void onDisable() {
    Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.DARK_RED + "The Plugin was disabled!");
  }

  private void loadConfig() {
    File dir = new File("plugins/Stacker");
    if (!dir.exists()) {
      dir.mkdirs();
    }
    if ((new File("plugins/Stacker/config.yml")).exists()) {
      Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + "Config file loaded!");
    } else {
      saveDefaultConfig();
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
    if (!loadConfig.autoUpdate()) return;
    try {
      File updateFolder = Bukkit.getUpdateFolderFile();
      updateFolder.mkdirs();
      Files.copy(new URI("https://github.com/ZoeyVid/Stacker/releases/latest/download/Stacker.jar").toURL().openStream(), new File(updateFolder, getFile().getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
      Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.WHITE + "Update downloaded, it will be applied on the next restart!");
    } catch (Exception e) {
      Bukkit.getConsoleSender().sendMessage(main.getPrefix() + ChatColor.RED + "Error while updating the plugin!");
    }
  }

  private void commandRegistration() {
    StackerCommand stackerCommand = new StackerCommand();
    getCommand("stacker").setExecutor(stackerCommand);
    getCommand("stacker").setTabCompleter(stackerCommand);
  }
}
