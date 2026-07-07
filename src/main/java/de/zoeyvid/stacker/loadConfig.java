package de.zoeyvid.stacker;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class loadConfig {
  private static final File config = new File("plugins/Stacker", "config.yml");
  private static FileConfiguration cfg;

  public static boolean defaultON() {
    return cfg.getBoolean("defaultOn", true);
  }

  public static boolean showMessage() {
    return cfg.getBoolean("showMessage", true);
  }

  public static boolean sendMessage() {
    return cfg.getBoolean("sendMessage", true);
  }

  public static boolean saveState() {
    return cfg.getBoolean("saveState", true);
  }

  public static boolean defaultImmune() {
    return cfg.getBoolean("defaultImmune", false);
  }

  public static boolean throwable() {
    return cfg.getBoolean("throwable", true);
  }

  public static boolean throwfalldamage() {
    return cfg.getBoolean("throwfalldamage", false);
  }

  public static String language() {
    return cfg.getString("languageFile", "en");
  }

  public static boolean autoUpdateLanguage() {
    return cfg.getBoolean("autoUpdateLanguage", true);
  }

  public static boolean autoUpdate() {
    return cfg.getBoolean("autoUpdate", false);
  }

  public static void readConfig() {
    cfg = YamlConfiguration.loadConfiguration(config);
  }
}
