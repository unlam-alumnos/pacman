package edu.unlam.pacman.shared.comunication.bus.events;

import edu.unlam.pacman.shared.model.Coordenada;

/**
 * Created by gmartin on 30/10/2015.
 */
public class DeadEvent extends GameEvent {
    private Coordenada coordenada;

    public DeadEvent(String subject, Coordenada coordenada){
        super(subject);
        coordenada = coordenada;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }
}
