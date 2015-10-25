package edu.unlam.pacman.client.modules.juego.personaje;

import java.awt.Graphics2D;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;
import edu.unlam.pacman.shared.model.Direction;

public abstract class PersonajeView<M> extends View<PersonajeView.MyView> {
    public interface MyView extends UiHandler {
        void move(Direction direction);
        void changeDirection(Direction direction);
        void paintPersonaje();
    }
    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        uiHandler().paintPersonaje();
    }

    @Override
    protected int getViewWidth() {
        return JuegoConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return JuegoConstants.MAX_HEIGHT;
    }

    public abstract void paintPersonaje(Personaje personaje);
}
