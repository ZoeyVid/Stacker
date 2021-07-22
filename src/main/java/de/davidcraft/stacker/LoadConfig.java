package de.davidcraft.stacker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LoadConfig {
    private static final File config = new File("plugins/Stacker", "config.yml");
    private static FileConfiguration cfg;

    public static boolean defaultON() {
        return cfg.getBoolean("defaultOn");
    }

    public static boolean showMessage() {
        return cfg.getBoolean("showMessage");
    }

    public static boolean sendMessage() {
        return cfg.getBoolean("sendMessage");
    }

    public static boolean saveState() {
        return cfg.getBoolean("saveState");
    }

    public static boolean defaultImmune() {
        return cfg.getBoolean("defaultImmune");
    }

    public static void ReadConfig() {
        cfg = YamlConfiguration.loadConfiguration(config);
    }
}
