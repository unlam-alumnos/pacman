package edu.unlam.pacman.client.modules.juego.tablero;

import edu.unlam.pacman.shared.common.Coordenada;

public class Casillero {
	public enum Tipo {
		FRUTA, FRUTA_ESPECIAL, PARED, PISO
	}

	private Coordenada origen;
	private int alto;
	private int ancho;
    private Tipo tipo;

	public Casillero(Coordenada origen, int alto, int ancho, Tipo tipo) {
		this.origen = origen;
		this.alto = alto;
		this.ancho = ancho;
		this.tipo = tipo;
	}

	public boolean contains(Coordenada coordenada) {
		return coordenada.getX() >= origen.getX() && coordenada.getX() <= origen.getX() + ancho
				&& coordenada.getY() >= origen.getY() && coordenada.getY() <= origen.getY() + alto;
	}

	public Coordenada getOrigen() {
		return origen;
	}

	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}

	public Tipo getTipo() {
		return tipo;
	}
}
