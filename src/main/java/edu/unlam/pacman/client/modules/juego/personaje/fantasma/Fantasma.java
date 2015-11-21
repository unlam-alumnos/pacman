package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class Fantasma extends Personaje {

    private String color;
    private long timeBlock;

    public Fantasma(Jugador jugador, Coordenada origen, Color color) {
        this.timeBlock = 0;
        setSpeed(130);
        setX(origen.getX());
        setY(origen.getY());
        setDirection(Direction.NONE);
        setStatus(Status.NORMAL);
        setCoordenadaPuntaje(new Coordenada(100,450));
        setColor(color.name().toLowerCase());
        setJugador(jugador);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void setTimeBlock(long timeBlock) {
        this.timeBlock = timeBlock;
    }

    @Override
    public long getTimeBlock(){
        return this.timeBlock;
    }

    @Override
    public String getTipoPersonaje() {
        return this.getClass().toString().substring(63).toString();
    }

}
