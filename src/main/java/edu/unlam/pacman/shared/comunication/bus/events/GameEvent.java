package edu.unlam.pacman.shared.comunication.bus.events;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:29
 */
public abstract class GameEvent {
    protected String subject;

    protected GameEvent() {}

    protected GameEvent(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
