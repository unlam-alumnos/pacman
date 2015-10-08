package edu.unlam.pacman.comunication.bus.async;

import edu.unlam.pacman.comunication.bus.events.GameEvent;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class Callback<T extends GameEvent> {
    private T event;

    public Callback(T event) {
        this.event = event;
    }

    public T getEvent() {
        return event;
    }
}
