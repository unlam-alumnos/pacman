package edu.unlam.pacman.ui.pacman;

import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.events.MoveEvent;
import edu.unlam.pacman.ui.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class PacmanPresenter extends Presenter<Pacman, PacmanView> {
    public PacmanPresenter() {
        super(new Pacman(), new PacmanView());
        updateCoordenates();
    }

    @Subscribe
    public void handleMovement(MoveEvent event) {
        getModel().setX(getModel().getX() + event.getX());
        getModel().setY(getModel().getY() + event.getY());
        updateCoordenates();
        getView().repaint();
    }

    private void updateCoordenates() {
        getView().updateCoordenates(getModel().getX(), getModel().getY(), getModel().getHeight(), getModel().getWidth());
    }
}
