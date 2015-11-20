package edu.unlam.pacman.client.modules.juego.tablero.puntaje;

import java.util.HashSet;
import java.util.Set;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.events.ScoreEvent;


/**
 * Created by gmartin on 27/10/2015.
 */
public class PuntajePresenter extends Presenter<PuntajeView> implements PuntajeView.MyView {
    Set personajes = new HashSet();

    public PuntajePresenter() {
        super(new PuntajeView());
    }

    @Subscribe
    public void handleScoreEventCallback(ScoreEvent e) {
        personajes.addAll(e.getPersonajes());
    }

    public void paintPuntaje() {
        if (personajes != null){
            int i = 0;
            for (Object personaje : personajes){
                getView().paintPuntaje((Personaje) personaje, i);
                i++;
            }
        }
    }
}
