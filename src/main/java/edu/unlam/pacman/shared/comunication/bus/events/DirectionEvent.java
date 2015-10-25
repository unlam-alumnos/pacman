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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DirectionEvent that = (DirectionEvent) o;

        if (origen != null ? !origen.equals(that.origen) : that.origen != null) {
            return false;
        }
        return direccion == that.direccion;
    }

    @Override
    public int hashCode() {
        int result = origen != null ? origen.hashCode() : 0;
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        return result;
    }
}
