package dev.sancraft.stacker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class loadLanguage {
    private static final File language = new File("plugins/Stacker/language", loadConfig.language() + ".yml");
    private static FileConfiguration lang;

    public static String dontStack() {
        return lang.getString("dontStack");
    }

    public static String leaveStackmode() {
        return lang.getString("leaveStackmode");
    }

    public static String joinStackmode() {
        return lang.getString("joinStackmode");
    }

    public static String permissionError() {
        return lang.getString("permissionError");
    }

    public static String helpTitle() {
        return lang.getString("helpTitle");
    }

    public static String helpHelp() {
        return lang.getString("helpHelp");
    }

    public static String helpNormal() {
        return lang.getString("helpNormal");
    }

    public static String helpToggle() {
        return lang.getString("helpToggle");
    }

    public static String helpImmune() {
        return lang.getString("helpImmune");
    }

    public static String leaveImmune() {
        return lang.getString("leaveImmune");
    }

    public static String joinImmune() {
        return lang.getString("joinImmune");
    }

    public static String errorPlayerOnline() {
        return lang.getString("errorPlayerOnline");
    }

    public static String leaveStackmodeOther() {
        return lang.getString("leaveStackmodeOther");
    }

    public static String joinStackmodeOther() {
        return lang.getString("joinStackmodeOther");
    }

    public static String leaveStackmodeSelf() {
        return lang.getString("leaveStackmodeSelf");
    }

    public static String joinStackmodeSelf() {
        return lang.getString("joinStackmodeSelf");
    }

    public static String leaveImmuneOther() {
        return lang.getString("leaveImmmuneOther");
    }

    public static String joinImmuneOther() {
        return lang.getString("joinImmmuneOther");
    }

    public static String leaveImmuneSelf() {
        return lang.getString("leaveImmmuneSelf");
    }

    public static String joinImmuneSelf() {
        return lang.getString("joinImmmuneSelf");
    }

    public static void readLanguage() {
        lang = YamlConfiguration.loadConfiguration(language);
    }
}
