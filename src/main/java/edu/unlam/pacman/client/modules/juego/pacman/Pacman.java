package edu.unlam.pacman.client.modules.juego.pacman;

import edu.unlam.pacman.client.mvp.Model;
import edu.unlam.pacman.shared.model.Direction;

import java.util.UUID;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class Pacman implements Model {
    private String id;
    private boolean active;

    private int x = 50;
    private int y = 50;
    private int height = 50;
    private int width = 50;
    private int speed = 130;
    private Direction direction = Direction.NONE;

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

    public int getSpeed() {
        return speed;
    }
}
