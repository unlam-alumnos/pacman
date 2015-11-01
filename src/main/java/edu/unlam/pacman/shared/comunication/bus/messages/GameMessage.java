package edu.unlam.pacman.shared.comunication.bus.messages;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.comunication.bus.events.GameEvent;

/**
 * Created by gmartin on 30/10/2015.
 */
public class GameMessage<T> extends GameEvent {
    private Jugador sender;
    private T message;
    private String type;

    public GameMessage() {
        // Needed by Gson
    }

    public GameMessage(Jugador sender, T message, String type) {
        this.sender = sender;
        this.message = message;
        this.type = type;
    }

    public Jugador getSender() {
        return sender;
    }

    public T getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
