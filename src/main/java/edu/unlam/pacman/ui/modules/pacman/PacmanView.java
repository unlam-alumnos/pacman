package edu.unlam.pacman.ui.modules.pacman;

import edu.unlam.pacman.common.Direction;
import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PacmanView extends View<PacmanView.MyView> implements KeyListener {
    interface MyView extends UiHandler {
        void move(Direction direction);
        void paintPacman();
    }

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        uiHandler().paintPacman();
    }

    public void paintPacman(int x, int y, int width, int height, Direction direction) {
        graphics().setColor(Color.YELLOW);
        int angle = 30;
        if (Direction.LEFT.equals(direction)) {
            angle = 210;
        } else if (Direction.UP.equals(direction)) {
            angle = 120;
        } else if (Direction.DOWN.equals(direction)) {
            angle = 300;
        }
        graphics().fillArc(x, y, width, height, angle, 300);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            uiHandler().move(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            uiHandler().move(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            uiHandler().move(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            uiHandler().move(Direction.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
