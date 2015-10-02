package edu.unlam.pacman.model;

public class Casillero {
	
	private enum tipo { FRUTA, FRUTA_ESPECIAL, PARED, PISO };
    private tipo casillero;

	public Casillero(tipo casillero) {
		super();
		this.casillero = casillero;
	}

	public void setTipo(short tipo) {
		this.casillero = casillero;
	}
	
	
}
