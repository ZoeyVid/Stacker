package de.zoeyvid.stacker;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class loadConfig {

  private static final File config = new File("plugins/Stacker", "config.yml");
  private static FileConfiguration cfg;

  public static boolean defaultON() {
    if (cfg.isBoolean("defaultON")) {
      return cfg.getBoolean("defaultOn");
    } else {
      cfg.set("defaultOn", true);
      saveConfig();
      return cfg.getBoolean("defaultOn");
    }
  }

  public static boolean showMessage() {
    if (cfg.isBoolean("showMessage")) {
      return cfg.getBoolean("showMessage");
    } else {
      cfg.set("showMessage", true);
      saveConfig();
      return cfg.getBoolean("showMessage");
    }
  }

  public static boolean sendMessage() {
    if (cfg.isBoolean("sendMessage")) {
      return cfg.getBoolean("sendMessage");
    } else {
      cfg.set("sendMessage", true);
      saveConfig();
      return cfg.getBoolean("sendMessage");
    }
  }

  public static boolean saveState() {
    if (cfg.isBoolean("saveState")) {
      return cfg.getBoolean("saveState");
    } else {
      cfg.set("saveState", true);
      saveConfig();
      return cfg.getBoolean("saveState");
    }
  }

  public static boolean defaultImmune() {
    if (cfg.isBoolean("defaultImmune")) {
      return cfg.getBoolean("defaultImmune");
    } else {
      cfg.set("defaultImmune", true);
      saveConfig();
      return cfg.getBoolean("defaultImmune");
    }
  }

  public static boolean throwable() {
    if (cfg.isBoolean("throwable")) {
      return cfg.getBoolean("throwable");
    } else {
      cfg.set("throwable", true);
      saveConfig();
      return cfg.getBoolean("throwable");
    }
  }

  public static boolean throwfalldamage() {
    if (cfg.isBoolean("throwfalldamage")) {
      return cfg.getBoolean("throwfalldamage");
    } else {
      cfg.set("throwfalldamage", false);
      saveConfig();
      return cfg.getBoolean("throwfalldamage");
    }
  }

  public static void readConfig() {
    cfg = YamlConfiguration.loadConfiguration(config);
  }

  public static String language() {
    if (cfg.isString("languageFile")) {
      return cfg.getString("languageFile");
    } else {
      cfg.set("languageFile", "en");
      saveConfig();
      return cfg.getString("languageFile");
    }
  }

  public static Boolean autoUpdate() {
    if (cfg.isBoolean("autoUpdate")) {
      return cfg.getBoolean("autoUpdate");
    } else {
      cfg.set("autoUpdate", true);
      saveConfig();
      return cfg.getBoolean("autoUpdate");
    }
  }

  public static String updateChannel() {
    if (cfg.isString("updateChannel")) {
      return cfg.getString("updateChannel");
    } else {
      cfg.set("updateChannel", "stable");
      saveConfig();
      return cfg.getString("updateChannel");
    }
  }

  public static Boolean autoUpdateLanguage() {
    if (cfg.isBoolean("autoUpdateLanguage")) {
      return cfg.getBoolean("autoUpdateLanguage");
    } else {
      cfg.set("autoUpdateLanguage", true);
      saveConfig();
      return cfg.getBoolean("autoUpdateLanguage");
    }
  }

  public static void saveConfig() {
    try {
      cfg.save(config);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
