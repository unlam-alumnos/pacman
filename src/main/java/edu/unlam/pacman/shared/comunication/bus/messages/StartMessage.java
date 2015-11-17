package edu.unlam.pacman.shared.comunication.bus.messages;

import java.util.LinkedHashSet;

import edu.unlam.pacman.client.modules.login.login.Jugador;

public class StartMessage implements BaseMessage {
    private LinkedHashSet<Jugador> jugadores;

    public StartMessage(LinkedHashSet<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public LinkedHashSet<Jugador> getJugadores() {
        return jugadores;
    }
}
