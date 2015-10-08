package edu.unlam.pacman.ui.modules.pacman;

import java.util.UUID;

import edu.unlam.pacman.common.Direction;
import edu.unlam.pacman.ui.mvp.Model;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class Pacman implements Model {
    private String id;
    private boolean active;

    private int x = 50;
    private int y = 50;
    private int height = 40;
    private int width = 40;
    private Direction direction = Direction.RIGHT;

    public Pacman() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
