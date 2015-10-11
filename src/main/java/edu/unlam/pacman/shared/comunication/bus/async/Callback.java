package edu.unlam.pacman.shared.comunication.bus.async;

import edu.unlam.pacman.shared.comunication.bus.events.GameEvent;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Callback<?> callback = (Callback<?>) o;

        return !(event != null ? !event.equals(callback.event) : callback.event != null);
    }

    @Override
    public int hashCode() {
        return event != null ? event.hashCode() : 0;
    }
}
