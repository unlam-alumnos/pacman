package edu.unlam.pacman.shared.comunication.bus.messages.async;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.comunication.bus.messages.BaseMessage;

public class AutenticarJugadorMessageCallback implements BaseMessage, AsyncMessage {
    private String sender;
    private Jugador jugador;

    public AutenticarJugadorMessageCallback() {
    }

    public AutenticarJugadorMessageCallback(String sender, Jugador jugador) {
        this.sender = sender;
        this.jugador = jugador;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
