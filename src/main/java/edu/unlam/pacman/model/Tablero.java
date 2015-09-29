package edu.unlam.pacman.model;

import java.util.List;

public class Tablero {
	
	private Casillero[][] tablero;
    private List<Personaje> personajes;
	
	public Tablero(Casillero[][] tablero, List<Personaje> personajes) {
		super();
		this.tablero = tablero;
        this.personajes = personajes;
    }

	public void dibujarse(){

	}

    public Coordenada dondeRevivir(){
        int x = 0;
        int y = 0;

        // TODO : caclcular la posicion mas alejada de todos los fantasmas dentro del tablero

        return new Coordenada(x,y);
    }
}
