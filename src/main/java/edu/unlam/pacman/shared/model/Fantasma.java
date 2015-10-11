package edu.unlam.pacman.shared.model;

import edu.unlam.pacman.client.modules.juego.tablero.Casillero;

public class Fantasma extends Personaje{
	
	private boolean bloqueado;
	private int victimas;
	
	public Fantasma() {
		super();
		this.bloqueado = false;
	}

	public void dibujarse(){
		
	}
	
	public void moverse(){
		
	}
	
	public void comer(Personaje pacman){
		
		victimas++;
	}
	
	public void comer(Casillero frutaEspecial){
		
	}
	
	public void morir(){
		
	}
	
	public void chocar(){
		this.bloqueado = true;
	}
}
