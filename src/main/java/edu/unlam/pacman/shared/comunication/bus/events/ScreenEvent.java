package edu.unlam.pacman.shared.comunication.bus.events;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class ScreenEvent extends GameEvent {
    public enum ScreenType {
        LOGIN, REGISTRO, MENU, JUEGO, ESTADISTICAS, RESULTADO
    }

    private ScreenType screenType;
    private Map<String, Object> params;

    public ScreenEvent(ScreenType screenType) {
        this(screenType, new HashMap<String, Object>());
    }

    public ScreenEvent(ScreenType screenType, Map<String, Object> params) {
        this.screenType = screenType;
        this.params = params;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
