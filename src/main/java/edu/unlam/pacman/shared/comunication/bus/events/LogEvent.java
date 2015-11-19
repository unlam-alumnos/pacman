package edu.unlam.pacman.shared.comunication.bus.events;

public class LogEvent extends GameEvent {
    private String message;

    public LogEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
