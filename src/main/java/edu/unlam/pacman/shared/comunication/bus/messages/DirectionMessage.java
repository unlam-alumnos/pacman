package edu.unlam.pacman.shared.comunication.bus.messages;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.shared.model.Direction;

/**
 * Created by gmartin on 30/10/2015.
 */
public class DirectionMessage extends BaseMessage {
    private Direction direction;
    private Personaje personaje;

    public DirectionMessage() {
    }

    public DirectionMessage(Direction direction, Personaje personaje) {
        this.direction = direction;
        this.personaje = personaje;
    }

    public Direction getDirection() {
        return direction;
    }

    public Personaje getPersonaje() {
        return personaje;
    }
}
