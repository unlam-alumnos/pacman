package edu.unlam.pacman.shared.comunication.bus.messages;

import java.util.List;

import edu.unlam.pacman.client.modules.login.login.Jugador;

public class StartMessage implements BaseMessage {
    private List<Jugador> jugadores;

    public StartMessage(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
}
