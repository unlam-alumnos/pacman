package edu.unlam.pacman.shared.util;

import java.util.prefs.Preferences;

/**
 * @author Cristian Miranda
 * @since 10/13/15 - 00:00
 */
public class PropertiesUtils {
    private final static Preferences preferences = Preferences.userRoot().node("game.properties");

    public static Preferences pref() {
        return preferences;
    }
}
