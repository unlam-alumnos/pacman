package edu.unlam.pacman.client.modules.juego.personaje.pacman;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class Pacman extends Personaje {

    private short imageIndex = 1;

    public Pacman() {
        setSpeed(140);
        setX(25);
        setY(25);
        setDirection(Direction.NONE);
        setStatus(Status.NORMAL);
        setCoordenadaPuntaje(new Coordenada(25,450));
    }

    public void setImageIndex(short i){
        this.imageIndex = i;
    }

    public void addImageIndex(){
        this.imageIndex++;
    }

    public short getImageIndex(){
        return imageIndex;
    }

    @Override
    public String getTipoPersonaje() {
        return this.getClass().toString().substring(61).toString();
    }
}
