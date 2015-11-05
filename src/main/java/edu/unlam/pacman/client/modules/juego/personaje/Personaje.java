package edu.unlam.pacman.client.modules.juego.personaje;

import java.io.Serializable;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

/**
 * Created by gmartin on 22/10/2015.
 */
public class Personaje implements Serializable {

    private Jugador jugador;
    private boolean active;

    private int x;
    private int y;
    private int height = 25;
    private int width = 25;
    private int speed;
    private Direction direction;
    private Status status;
    private int kills=0;
    private Coordenada coordenadaPuntaje;

    public Personaje() {

    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void dead(Coordenada coordenada){
        setX(coordenada.getX());
        setY(coordenada.getY());
        setStatus(Status.NORMAL);
        setDirection(Direction.NONE);
    }

    public int getKills (){
        return this.kills;
    }

    public void increaseKill(){
        kills++;
    }

    public Coordenada getCoordenadaPuntaje() {
        return coordenadaPuntaje;
    }

    public void setCoordenadaPuntaje(Coordenada coordenadaPuntaje) {
        this.coordenadaPuntaje = coordenadaPuntaje;
    }

    public String getTipoPersonaje() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Personaje personaje = (Personaje) o;

        if (active != personaje.active) {
            return false;
        }
        if (x != personaje.x) {
            return false;
        }
        if (y != personaje.y) {
            return false;
        }
        if (height != personaje.height) {
            return false;
        }
        if (width != personaje.width) {
            return false;
        }
        if (speed != personaje.speed) {
            return false;
        }
        if (kills != personaje.kills) {
            return false;
        }
        if (jugador != null ? !jugador.equals(personaje.jugador) : personaje.jugador != null) {
            return false;
        }
        if (direction != personaje.direction) {
            return false;
        }
        if (status != personaje.status) {
            return false;
        }
        return !(coordenadaPuntaje != null ? !coordenadaPuntaje.equals(personaje.coordenadaPuntaje) : personaje.coordenadaPuntaje != null);
    }

    @Override
    public int hashCode() {
        int result = jugador != null ? jugador.hashCode() : 0;
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + speed;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + kills;
        result = 31 * result + (coordenadaPuntaje != null ? coordenadaPuntaje.hashCode() : 0);
        return result;
    }
}
