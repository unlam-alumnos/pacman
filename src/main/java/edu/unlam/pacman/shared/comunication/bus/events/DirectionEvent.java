package edu.unlam.pacman.shared.comunication.bus.events;

import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;

/**
 * Created by gmartin on 24/10/2015.
 */
public class DirectionEvent extends GameEvent {
    private Coordenada origen;
    private Direction direccion;

    public DirectionEvent(String subject, Coordenada origen, Direction direccion) {
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
