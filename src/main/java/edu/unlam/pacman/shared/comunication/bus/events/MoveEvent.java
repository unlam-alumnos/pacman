package edu.unlam.pacman.shared.comunication.bus.events;

import edu.unlam.pacman.shared.common.Coordenada;
import edu.unlam.pacman.shared.common.Direction;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class MoveEvent extends GameEvent {
    private Coordenada origen;
    private Direction direccion;

    public MoveEvent(String subject, Coordenada origen, Direction direccion) {
        super(subject);
        this.origen = origen;
        this.direccion = direccion;
    }

    public Coordenada getOrigen() {
        return origen;
    }

    public Direction getDireccion() {
        return direccion;
    }
}
