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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MoveEvent moveEvent = (MoveEvent) o;

        if (origen != null ? !origen.equals(moveEvent.origen) : moveEvent.origen != null) {
            return false;
        }
        if (direccion != moveEvent.direccion) {
            return false;
        }
        return !(personajeType != null ? !personajeType.equals(moveEvent.personajeType) : moveEvent.personajeType != null);
    }

    @Override
    public int hashCode() {
        int result = origen != null ? origen.hashCode() : 0;
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (personajeType != null ? personajeType.hashCode() : 0);
        return result;
    }
}
