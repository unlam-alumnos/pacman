package edu.unlam.pacman.shared.comunication.bus.messages;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.comunication.bus.events.GameEvent;

/**
 * Created by gmartin on 30/10/2015.
 */
public class GameMessage extends GameEvent {
    private Jugador sender;
    private BaseMessage message;

    public GameMessage() {
        // Needed by Gson
    }

    public GameMessage(Jugador sender, BaseMessage message) {
        this.sender = sender;
        this.message = message;
    }

    public Jugador getSender() {
        return sender;
    }

    public BaseMessage getMessage() {
        return message;
    }
}
