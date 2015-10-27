package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class Fantasma extends Personaje {

    private String color;

    public Fantasma() {
        setSpeed(130);
        setX(100);
        setY(100);
        setDirection(Direction.NONE);
        setStatus(Status.NORMAL);
        setCoordenadaPuntaje(new Coordenada(100,450));
        setColor("celeste");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getTipoPersonaje() {
        return this.getClass().toString().substring(63).toString();
    }

}
