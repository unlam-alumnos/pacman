package edu.unlam.pacman.shared.util;

import java.util.prefs.Preferences;

/**
 * @author Cristian Miranda
 * @since 10/13/15 - 00:00
 */
public class PropertiesUtils {
    public static Preferences pref() {
        return Preferences.userRoot().node("game.properties");
    }
}
