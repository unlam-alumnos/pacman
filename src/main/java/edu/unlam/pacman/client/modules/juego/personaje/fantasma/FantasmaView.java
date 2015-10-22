package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;
import edu.unlam.pacman.shared.model.Direction;

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

    public void paintFantasma(int x, int y, int width, int height, Direction direction) {
        ImageIcon sprite = null;
        String directionFolder = "celeste";

        sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + directionFolder + "/right.png");
        if (Direction.LEFT.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + directionFolder +"/left.png");
        } else if (Direction.UP.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + directionFolder + "/up.png");
        } else if (Direction.DOWN.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + directionFolder + "/down.png");
        } else if(Direction.NONE.equals(direction)) {
            sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + directionFolder + "/right.png");
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
