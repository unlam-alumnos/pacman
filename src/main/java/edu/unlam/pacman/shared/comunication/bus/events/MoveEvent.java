package edu.unlam.pacman.shared.comunication.bus.events;

import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class MoveEvent extends GameEvent {
    private Coordenada origen;
    private Direction direccion;
    private String personajeType;

    public MoveEvent(String subject, Coordenada origen, Direction direccion, String tipo) {
        super(subject);
        this.origen = origen;
        this.direccion = direccion;
        this.personajeType = tipo;
    }

    public Coordenada getOrigen() {
        return origen;
    }

    public Direction getDireccion() {
        return direccion;
    }

    public String getPersonajeType() {
        return personajeType;
    }
}
