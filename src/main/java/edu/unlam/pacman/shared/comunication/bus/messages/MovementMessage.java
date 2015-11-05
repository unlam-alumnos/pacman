package edu.unlam.pacman.shared.comunication.bus.messages;

import edu.unlam.pacman.shared.model.Direction;

public class MovementMessage implements BaseMessage {
    private int x;
    private int y;
    private Direction direction;

    public MovementMessage() {
    }

    public MovementMessage(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}
