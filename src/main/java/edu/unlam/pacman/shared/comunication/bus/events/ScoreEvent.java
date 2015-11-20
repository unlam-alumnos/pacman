package edu.unlam.pacman.shared.comunication.bus.events;

import java.util.Set;

/**
 * Created by gmartin on 27/10/2015.
 */
public class ScoreEvent extends GameEvent {
    private Set personajes;

    public ScoreEvent(Set pj){
        this.personajes = pj;
    }

    public Set getPersonajes() {
        return personajes;
    }
}
