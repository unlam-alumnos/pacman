package edu.unlam.pacman.ui.pacman;

import edu.unlam.pacman.ui.Model;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class Pacman implements Model {
    private int x = 225;
    private int y = 225;
    private int height = 40;
    private int width = 40;

    public Pacman() {
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

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
