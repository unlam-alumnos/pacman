package edu.unlam.pacman.shared.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cristian Miranda
 * @since 10/13/15 - 00:00
 */
public class PropertiesUtils {
    private static final Map<String, String> properties = new HashMap<>();
    public static Map<String, String> pref() {
        return properties;
        // return Preferences.userRoot().node("game.properties");
    }
}
