package edu.unlam.pacman.ui.modules.fantasma;

import edu.unlam.pacman.events.Bus;
import edu.unlam.pacman.events.MoveEvent;
import edu.unlam.pacman.ui.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class FantasmaPresenter extends Presenter<FantasmaView> implements FantasmaView.MyView {
    private Fantasma pacman;

    public FantasmaPresenter() {
        super(new FantasmaView());
        this.pacman = new Fantasma();
    }

    @Override
    public void move(int x, int y) {
        pacman.setX(pacman.getX() + x);
        pacman.setY(pacman.getY() + y);
        paintPacman();
        Bus.post(new MoveEvent(x, y));
    }

    @Override
    public void paintPacman() {
        getView().paintPacman(pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight());
        getView().repaint();
    }
}
