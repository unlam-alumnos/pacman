package edu.unlam.pacman.client.modules.juego.puntaje;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.mvp.Presenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmartin on 27/10/2015.
 */
public class PuntajePresenter extends Presenter<PuntajeView> implements PuntajeView.MyView {

    public PuntajePresenter() {
        super(new PuntajeView());
    }



    @Override
    public void paint() {

    }
}
