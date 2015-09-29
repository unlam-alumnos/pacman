package edu.unlam.pacman.model;

/**
 * Created by gmartin on 27/09/2015.
 */
public class Coordenada {
    private int posicionX;
    private int posicionY;

    public Coordenada(int posicionY, int posicionX) {
        this.posicionY = posicionY;
        this.posicionX = posicionX;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
}
