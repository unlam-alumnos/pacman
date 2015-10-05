package edu.unlam.pacman.ui.modules.pacman;

import edu.unlam.pacman.ui.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class PacmanPresenter extends Presenter<PacmanView> implements PacmanView.MyView {
    private Pacman pacman;
    
    public PacmanPresenter() {
        super(new PacmanView());
        this.pacman = new Pacman();
        updateCoordenates();
    }

    private void updateCoordenates() {
        getView().updateCoordenates(pacman.getX(), pacman.getY(), pacman.getHeight(), pacman.getWidth());
    }

    @Override
    public void move(int x, int y) {
        pacman.setX(pacman.getX() + x);
        pacman.setY(pacman.getY() + y);
        updateCoordenates();
        getView().repaint();
    }
}
