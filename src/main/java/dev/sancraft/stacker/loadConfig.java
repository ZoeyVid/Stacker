package dev.sancraft.stacker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class loadConfig {
    private static final File config = new File("plugins/Stacker", "config.yml");
    private static FileConfiguration cfg;

    public static boolean defaultON() {
        if (cfg.isBoolean("defaultON")) {
            return cfg.getBoolean("defaultOn");
        } else {
            cfg.addDefault("defaultOn", false);
            return false;
        }
    }

    public static boolean showMessage() {
        if (cfg.isBoolean("showMessage")) {
            return cfg.getBoolean("showMessage");
        } else {
            cfg.addDefault("showMessage", true);
            return true;
        }
    }

    public static boolean sendMessage() {
        if (cfg.isBoolean("sendMessage")) {
            return cfg.getBoolean("sendMessage");
        } else {
            cfg.addDefault("sendMessage", true);
            return true;
        }
    }

    public static boolean saveState() {
        if (cfg.isBoolean("saveState")) {
            return cfg.getBoolean("saveState");
        } else {
            cfg.addDefault("saveState", true);
            return true;
        }
    }

    public static boolean defaultImmune() {
        if (cfg.isBoolean("defaultImmune")) {
            return cfg.getBoolean("defaultImmune");
        } else {
            cfg.addDefault("defaultImmune", false);
            return false;
        }
    }

    public static void readConfig() {
        cfg = YamlConfiguration.loadConfiguration(config);
    }

    public static String language() {
        if (cfg.isString("languageFile")) {
            return cfg.getString("languageFile");
        } else {
            cfg.addDefault("languageFile", "en");
            return "en";
        }
    }

    public static Boolean autoUpdate() {
        if (cfg.isBoolean("autoUpdate")) {
            return cfg.getBoolean("autoUpdate");
        } else {
            cfg.addDefault("autoUpdate", true);
            return true;
        }
    }

    public static String updateChannel() {
        if (cfg.isString("updateChannel")) {
            return cfg.getString("updateChannel");
        } else {
            cfg.addDefault("updateChannel", "stable");
            return "stable";
        }
    }

    public static Boolean autoUpdateLanguage() {
        if (cfg.isBoolean("autoUpdateLanguage")) {
            return cfg.getBoolean("autoUpdateLanguage");
        } else {
            cfg.addDefault("autoUpdateLanguage", true);
            return true;
        }
    }
}
