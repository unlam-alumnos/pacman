package edu.unlam.pacman.comunication.bus.events;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class ScreenEvent extends GameEvent {
    public enum ScreenType {
        LOGIN, REGISTRO, MENU, JUEGO, ESTADISTICAS, RESULTADO
    }

    private ScreenType screenType;

    public ScreenEvent(ScreenType screenType) {
        this.screenType = screenType;
    }

    public ScreenType getScreenType() {
        return screenType;
    }
}
