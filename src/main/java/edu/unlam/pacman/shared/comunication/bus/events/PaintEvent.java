package edu.unlam.pacman.shared.comunication.bus.events;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;

/**
 * Created by gmartin on 22/10/2015.
 */
public class PaintEvent extends GameEvent {
    private Personaje personaje;

    public PaintEvent(Personaje personaje) {
        this.personaje = personaje;
    }

    public Personaje getPersonaje() {
        return personaje;
    }
}
