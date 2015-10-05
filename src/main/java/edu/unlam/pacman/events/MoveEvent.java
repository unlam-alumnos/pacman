package edu.unlam.pacman.events;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class MoveEvent implements GameEvent {
    private Integer x;
    private Integer y;

    public MoveEvent(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
