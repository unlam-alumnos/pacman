package edu.unlam.pacman.ui.modules.tablero;

import edu.unlam.pacman.common.Coordenada;

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
