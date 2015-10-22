package edu.unlam.pacman.client.modules.juego.personaje.pacman;

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
        void changeDirection(Direction direction);
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

    public void paintPacman(int x, int y, int width, int height, Direction direction, int imageIndex) {
        ImageIcon sprite = null;
        String directionFolder;

        switch (imageIndex) {
            case 1:
                directionFolder = "uno";
                break;
            case 2:
                directionFolder = "dos";
                break;
            case 3:
                directionFolder = "tres";
                break;
            case 4:
                directionFolder = "dos";
                break;
            case 5:
                directionFolder = "uno";
                break;
            default:
                directionFolder = "cero";
        }

        sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + directionFolder + "/right.gif");
        if (Direction.LEFT.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + directionFolder +"/left.gif");
        } else if (Direction.UP.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + directionFolder + "/up.gif");
        } else if (Direction.DOWN.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + directionFolder + "/down.gif");
        } else if(Direction.NONE.equals(direction)) {
            directionFolder = "uno";
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + directionFolder + "/right.gif");
        }
        graphics().drawImage(sprite.getImage(), x, y, width, height, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            uiHandler().changeDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            uiHandler().changeDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            uiHandler().changeDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            uiHandler().changeDirection(Direction.DOWN);
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
