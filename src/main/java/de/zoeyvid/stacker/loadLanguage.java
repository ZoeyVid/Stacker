package de.zoeyvid.stacker;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class loadLanguage {
  private static final File language = new File("plugins/Stacker/language", loadConfig.language() + ".yml");
  private static FileConfiguration lang;

  public static String dontStack() {
    return lang.getString("dontStack", "The Player %player% can`t be stacked!");
  }

  public static String leaveStackmode() {
    return lang.getString("leaveStackmode", "You are not longer in the stackmode!");
  }

  public static String joinStackmode() {
    return lang.getString("joinStackmode", "You are now in the stackmode!");
  }

  public static String permissionError() {
    return lang.getString("permissionError", "You dont have the Permission to use this Command! Missing: %permission%");
  }

  public static String helpTitle() {
    return lang.getString("helpTitle", "Stacker Help");
  }

  public static String helpHelp() {
    return lang.getString("helpHelp", " Shows this Help");
  }

  public static String helpToggle() {
    return lang.getString("helpToggle", " Toggle the stackmode for another player");
  }

  public static String helpImmune() {
    return lang.getString("helpImmune", " Toggle Immunemode for another Player or self");
  }

  public static String leaveImmune() {
    return lang.getString("leaveImmune", "You are not longer immune!");
  }

  public static String joinImmune() {
    return lang.getString("joinImmune", "You are now immune!");
  }

  public static String errorPlayerOnline() {
    return lang.getString("errorPlayerOnline", "You must name a online Player!");
  }

  public static String leaveStackmodeOther() {
    return lang.getString("leaveStackmodeOther", "The Player %player% is not longer in the stackmode!");
  }

  public static String joinStackmodeOther() {
    return lang.getString("joinStackmodeOther", "The Player %player% is now in the stackmode!");
  }

  public static String leaveStackmodeSelf() {
    return lang.getString("leaveStackmodeSelf", "You are not longer in the stackmode!");
  }

  public static String joinStackmodeSelf() {
    return lang.getString("joinStackmodeSelf", "You are now in the stackmode!");
  }

  public static String leaveImmuneOther() {
    return lang.getString("leaveImmuneOther", "The Player %player% is not longer immune!");
  }

  public static String joinImmuneOther() {
    return lang.getString("joinImmuneOther", "The Player %player% is now immune!");
  }

  public static String leaveImmuneSelf() {
    return lang.getString("leaveImmuneSelf", "You are not longer immune!");
  }

  public static String joinImmuneSelf() {
    return lang.getString("joinImmuneSelf", "You are now immune!");
  }

  public static void readLanguage() {
    lang = YamlConfiguration.loadConfiguration(language);
  }

  public static int getVersion() {
    return lang.getInt("version", 1);
  }
}
