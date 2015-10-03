package edu.unlam.pacman.model;

public class Casillero {
	
	private enum Tipo { FRUTA, FRUTA_ESPECIAL, PARED, PISO };
    private Tipo casillero;

	public Casillero(Tipo casillero) {
		super();
		this.casillero = casillero;
	}

	public void setTipo(short tipo) {
		this.casillero = casillero;
	}
	
	
}
