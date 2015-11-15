package edu.unlam.pacman.shared.comunication.bus.messages;

import edu.unlam.pacman.client.modules.login.login.Jugador;

public class HunterMessage implements BaseMessage {
    private Jugador jugador;

    public HunterMessage() {
    }

    public HunterMessage(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
