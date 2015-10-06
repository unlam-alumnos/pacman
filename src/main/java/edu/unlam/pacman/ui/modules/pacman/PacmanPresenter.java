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
    }

    @Override
    public void move(int x, int y) {
        pacman.setX(pacman.getX() + x);
        pacman.setY(pacman.getY() + y);
        paintPacman();
    }

    @Override
    public void paintPacman() {
        getView().paintPacman(pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight());
        getView().repaint();
    }
}
