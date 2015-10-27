package edu.unlam.pacman.client.modules.juego.puntaje;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.events.ScoreEvent;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by gmartin on 27/10/2015.
 */
public class PuntajePresenter extends Presenter<PuntajeView> implements PuntajeView.MyView {
    Set<Personaje> personajes;
    public PuntajePresenter() {
        super(new PuntajeView());
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleScoreEventCallback(ScoreEvent e) {
        System.out.println("Callback ScoreEvent");
        personajes  = e.getPersonajes();
        paint();
    }

    @Override
    public void paint() {
        if (personajes != null){
            for (Personaje personaje : personajes){
                getView().paint(personaje.getCoordenadaPuntaje().getX(), personaje.getCoordenadaPuntaje().getY(), personaje.getKills());
            }
        }

    }
}
