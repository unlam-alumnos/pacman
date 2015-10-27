package edu.unlam.pacman.client.modules.juego.puntaje;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;
import edu.unlam.pacman.shared.comunication.bus.events.ScoreEvent;

import java.awt.*;

/**
 * Created by gmartin on 27/10/2015.
 */
public class PuntajeView extends View<PuntajeView.MyView> {

    interface MyView extends UiHandler {
        void paint();
    }

    @Override
    protected void onBind() {
        setOpaque(true);
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        uiHandler().paint();
    }

    @Override
    protected int getViewWidth() {
        return JuegoConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return JuegoConstants.MAX_HEIGHT;
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleScoreEventCallback(ScoreEvent e) {
        Personaje pj = e.getPersonaje();

    }

    public void paint(int x, int y, int puntaje){
        graphics().setColor(Color.WHITE);
        graphics().setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Calibri", Font.BOLD, 25);
        graphics().setFont(font);
        graphics().drawString(Integer.toString(puntaje), x, y+41);
    }
}
