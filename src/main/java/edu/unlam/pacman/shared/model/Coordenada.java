package edu.unlam.pacman.shared.model;

/**
 * @author Cristian Miranda
 * @since 10/7/15 - 12:50
 */
public class Coordenada {
    private int x;
    private int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distancia(Coordenada other){
        double distanciaX = getX() - other.getX();
        double distanciaY = getY() - other.getY();
        double distancia = Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));
        return distancia;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
