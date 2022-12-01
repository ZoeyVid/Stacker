package de.zoeyvid.stacker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class loadLanguage {
    private static final File language = new File("plugins/Stacker/language", loadConfig.language() + ".yml");
    private static FileConfiguration lang;

    public static String dontStack() {
        if (lang.isString("dontStack")) {
            return lang.getString("dontStack");
        } else {
            lang.addDefault("dontStack", "The Player %player% can`t be stacked!");
            saveLanguage();
            return "The Player %player% can`t be stacked!";
        }
    }

    public static String leaveStackmode() {
        if (lang.isString("leaveStackmode")) {
            return lang.getString("leaveStackmode");
        } else {
            lang.addDefault("leaveStackmode", "You are not longer in the stackmode!");
            saveLanguage();
            return "You are not longer in the stackmode!";
        }
    }

    public static String joinStackmode() {
        if (lang.isString("joinStackmode")) {
            return lang.getString("joinStackmode");
        } else {
            lang.addDefault("joinStackmode", "You are now in the stackmode!");
            saveLanguage();
            return "You are now in the stackmode!";
        }
    }

    public static void saveLanguage() {
        try {
            lang.save(language);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String permissionError() {
        if (lang.isString("permissionError")) {
            return lang.getString("permissionError");
        } else {
            lang.addDefault("permissionError", "You dont have the Permission to use this Command! Missing: %permission%");
            saveLanguage();
            return "You dont have the Permission to use this Command! Missing: %permission%";
        }
    }

    public static String helpTitle() {
        if (lang.isString("helpTitle")) {
            return lang.getString("helpTitle");
        } else {
            lang.addDefault("helpTitle", "Stacker Help");
            saveLanguage();
            return "Stacker Help";
        }
    }

    public static String helpHelp() {
        if (lang.isString("helpHelp")) {
            return lang.getString("helpHelp");
        } else {
            lang.addDefault("helpHelp", "Shows this Help");
            saveLanguage();
            return "Shows this Help";
        }
    }

    public static String helpNormal() {
        if (lang.isString("helpNormal")) {
            return lang.getString("helpNormal");
        } else {
            lang.addDefault("helpNormal", " Toggle the stackmode");
            saveLanguage();
            return " Toggle the stackmode";
        }
    }

    public static String helpToggle() {
        if (lang.isString("helpToggle")) {
            return lang.getString("helpToggle");
        } else {
            lang.set("helpToggle", " Toggle the stackmode for another player");
            saveLanguage();
            return " Toggle the stackmode for another player";
        }
    }

    public static String helpImmune() {
        if (lang.isString("helpImmune")) {
            return lang.getString("helpImmune");
        } else {
            lang.set("helpImmune", " Toggle Immunemode for another Player or self");
            saveLanguage();
            return " Toggle Immunemode for another Player or self";
        }
    }

    public static String leaveImmune() {
        if (lang.isString("leaveImmune")) {
            return lang.getString("leaveImmune");
        } else {
            lang.set("leaveImmune", "You are not longer immune!");
            saveLanguage();
            return "You are not longer immune!";
        }
    }

    public static String joinImmune() {
        if (lang.isString("joinImmune")) {
            return lang.getString("joinImmune");
        } else {
            lang.set("joinImmune", "You are now immune!");
            saveLanguage();
            return "You are now immune!";
        }
    }

    public static String errorPlayerOnline() {
        if (lang.isString("errorPlayerOnline")) {
            return lang.getString("errorPlayerOnline");
        } else {
            lang.set("errorPlayerOnline", "You must name a online Player!");
            saveLanguage();
            return "You must name a online Player!";
        }
    }

    public static String leaveStackmodeOther() {
        if (lang.isString("leaveStackmodeOther")) {
            return lang.getString("leaveStackmodeOther");
        } else {
            lang.set("leaveStackmodeOther", "The Player %player% is not longer in the stackmode!");
            saveLanguage();
            return "The Player %player% is not longer in the stackmode!";
        }
    }

    public static String joinStackmodeOther() {
        if (lang.isString("joinStackmodeOther")) {
            return lang.getString("joinStackmodeOther");
        } else {
            lang.set("joinStackmodeOther", "The Player %player% is now in the stackmode!");
            saveLanguage();
            return "The Player %player% is now in the stackmode!";
        }
    }

    public static String leaveStackmodeSelf() {
        if (lang.isString("leaveStackmodeSelf")) {
            return lang.getString("leaveStackmodeSelf");
        } else {
            lang.set("leaveStackmodeSelf", "You are not longer in the stackmode!");
            saveLanguage();
            return "You are not longer in the stackmode!";
        }
    }

    public static String joinStackmodeSelf() {
        if (lang.isString("joinStackmodeSelf")) {
            return lang.getString("joinStackmodeSelf");
        } else {
            lang.set("joinStackmodeSelf", "You are now in the stackmode!");
            saveLanguage();
            return "You are now in the stackmode!";
        }
    }

    public static String leaveImmuneOther() {
        if (lang.isString("leaveImmuneOther")) {
            return lang.getString("leaveImmmuneOther");
        } else {
            lang.set("leaveImmuneOther", "The Player %player% is not longer immune!");
            saveLanguage();
            return "The Player %player% is not longer immune!";
        }
    }

    public static String joinImmuneOther() {
        if (lang.isString("joinImmuneOther")) {
            return lang.getString("joinImmmuneOther");
        } else {
            lang.set("joinImmuneOther", "The Player %player% is now immune!");
            saveLanguage();
            return "The Player %player% is now immune!";
        }
    }

    public static String leaveImmuneSelf() {
        if (lang.isString("leaveImmuneSelf")) {
            return lang.getString("leaveImmmuneSelf");
        } else {
            lang.set("leaveImmuneSelf", "You are not longer immune!");
            saveLanguage();
            return "You are not longer immune!";
        }
    }

    public static String joinImmuneSelf() {
        if (lang.isString("joinImmuneSelf")) {
            return lang.getString("joinImmmuneSelf");
        } else {
            lang.set("joinImmuneSelf", "You are now immune!");
            saveLanguage();
            return "You are now immune!";
        }
    }

    public static void readLanguage() {
        lang = YamlConfiguration.loadConfiguration(language);
    }

    public static int getVersion() {
        if (lang.isInt("version")) {
            return lang.getInt("version");
        } else {
            return 1;
        }
    }
}
