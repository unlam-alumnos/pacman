package edu.unlam.pacman.shared.comunication.bus.events;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;

/**
 * Created by gmartin on 27/10/2015.
 */
public class ScoreEvent extends GameEvent {
    private Personaje personaje;

    public Personaje getPersonaje() {
        return personaje;
    }
}
