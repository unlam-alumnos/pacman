package edu.unlam.pacman.client.modules.juego.personaje;

import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

import java.util.UUID;

/**
 * Created by gmartin on 22/10/2015.
 */
public abstract class Personaje {

    private String id;
    private boolean active;

    private int x;
    private int y;
    private int height = 25;
    private int width = 25;
    private int speed;
    private Direction direction;
    private Status status;

    public Personaje() {
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
