package edu.unlam.pacman.shared.comunication.bus.messages;

import edu.unlam.pacman.client.modules.login.login.Jugador;

public class JugadorMessage implements BaseMessage {
    private Jugador jugador;

    public JugadorMessage() {
    }

    public JugadorMessage(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
