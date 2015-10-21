package edu.unlam.pacman.client.modules.juego.pacman;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;
import edu.unlam.pacman.shared.model.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PacmanView extends View<PacmanView.MyView> implements KeyListener {
    interface MyView extends UiHandler {
        void move(Direction direction);
        void paintPacman();
    }
    private boolean flag = true;

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

    public void paintPacman(int x, int y, int width, int height, Direction direction, int turnoBoca) {
        ImageIcon sprite;
        String animacionBoca;

        switch (turnoBoca){
            case 1: animacionBoca = "uno";
                break;
            case 2: animacionBoca = "dos";
                break;
            case 3: animacionBoca = "tres";
                break;
            default: animacionBoca = "cero";
        }

        sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + animacionBoca + "/right.gif");
        if (Direction.LEFT.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + animacionBoca +"/left.gif");
        } else if (Direction.UP.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + animacionBoca + "/up.gif");
        } else if (Direction.DOWN.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + animacionBoca + "/down.gif");
        }
        graphics().drawImage(sprite.getImage(), x, y, width, height, this);
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

    @Override
    protected int getViewWidth() {
        return JuegoConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return JuegoConstants.MAX_HEIGHT;
    }
}
