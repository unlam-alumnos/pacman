package edu.unlam.pacman.shared.comunication.bus.events;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;

import java.util.Set;

/**
 * Created by gmartin on 27/10/2015.
 */
public class ScoreEvent extends GameEvent {
    private Set<Personaje> personajes;

    public ScoreEvent(Set<Personaje> pj){
        this.personajes = pj;
    }

    public Set<Personaje> getPersonajes() {
        return personajes;
    }
}
