package edu.unlam.pacman.shared.comunication.bus.messages;

import edu.unlam.pacman.shared.model.Coordenada;

public class FruitMessage implements BaseMessage {
    private Coordenada coordenada;

    public FruitMessage() {
    }

    public FruitMessage(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }
}
