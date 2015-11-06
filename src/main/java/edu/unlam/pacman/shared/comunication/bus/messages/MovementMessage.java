package edu.unlam.pacman.shared.comunication.bus.messages;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;

public class MovementMessage implements BaseMessage {
    private Personaje personaje;

    public MovementMessage() {
    }

    public MovementMessage(Personaje personaje) {
        this.personaje = personaje;
    }

    public Personaje getPersonaje() {
        return personaje;
    }
}
