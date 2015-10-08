package edu.unlam.pacman.comunication.bus.async;

import edu.unlam.pacman.comunication.bus.events.GameEvent;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class Request<T extends GameEvent> {
    private T event;

    public Request(T event) {
        this.event = event;
    }

    public T getEvent() {
        return event;
    }
}
