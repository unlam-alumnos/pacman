package edu.unlam.pacman.client.modules.juego.personaje.pacman;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

import javax.swing.*;
import java.awt.*;

public class PacmanView extends View<PacmanView.MyView> {
    interface MyView extends UiHandler {
        void move(Direction direction);
        void changeDirection(Direction direction);
        void paintPacman();
    }
    private boolean flag = true;

    @Override
    protected void onBind() {
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        uiHandler().paintPacman();
    }

    public void paintPacman(int x, int y, int width, int height, Direction direction, int imageIndex, Status status) {
        ImageIcon sprite = null;

        if(Status.VICTIM.equals(status)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/victim.gif");
        }else{
            switch (direction){
                case RIGHT:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + JuegoConstants.PACMAN_ANIMATION[imageIndex-1] + "/right.gif");
                    break;
                case LEFT:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + JuegoConstants.PACMAN_ANIMATION[imageIndex-1] +"/left.gif");
                    break;
                case UP:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + JuegoConstants.PACMAN_ANIMATION[imageIndex-1] + "/up.gif");
                    break;
                case DOWN:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/" + JuegoConstants.PACMAN_ANIMATION[imageIndex-1] + "/down.gif");
                    break;
                default:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/uno/right.gif");
                    break;
            }
        }
        graphics().drawImage(sprite.getImage(), x, y, width, height, this);
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
