package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

import javax.swing.*;
import java.awt.*;

public class FantasmaView extends View<FantasmaView.MyView> {
    interface MyView extends UiHandler {
        void move(Direction direction);
        void changeDirection(Direction direction);
        void paintFantasma();
    }
    private boolean flag = true;

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        uiHandler().paintFantasma();
    }

    public void paintFantasma(int x, int y, int width, int height, Direction direction, Status status) {
        ImageIcon sprite = null;
        String colorGhost = "celeste";

        if(Status.VICTIM.equals(status)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + colorGhost + "/victim.png");
        }else{
            switch (direction){
                case RIGHT:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + colorGhost + "/right.png");
                    break;
                case LEFT:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + colorGhost +"/left.png");
                    break;
                case UP:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + colorGhost + "/up.png");
                    break;
                case DOWN:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + colorGhost + "/down.png");
                    break;
                default:
                    sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + colorGhost + "/right.png");
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
