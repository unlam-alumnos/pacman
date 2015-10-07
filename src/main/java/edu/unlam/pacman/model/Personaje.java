package edu.unlam.pacman.model;

import javax.swing.ImageIcon;

import edu.unlam.pacman.common.Coordenada;

public abstract class Personaje {

	protected int velocidad;
	protected Coordenada posicion;
	protected ImageIcon imagen;
	
	protected void setImagen(){}
	
	protected void dibujarse(){}
	
	protected void moverse(){}
	
	protected void comer(Personaje personaje){}
	
	protected void morir(){}
}
