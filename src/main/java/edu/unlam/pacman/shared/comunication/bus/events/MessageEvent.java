package edu.unlam.pacman.shared.comunication.bus.events;

/**
 * Created by gmartin on 30/10/2015.
 */
public class MessageEvent extends GameEvent {
    private String data;

    public MessageEvent(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
