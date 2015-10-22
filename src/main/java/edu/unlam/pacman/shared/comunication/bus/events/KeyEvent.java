package edu.unlam.pacman.shared.comunication.bus.events;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class KeyEvent extends GameEvent {
    private int keyCode;

    public KeyEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
