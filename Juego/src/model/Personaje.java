package model;

import javax.swing.ImageIcon;

public abstract class Personaje {

	protected int velocidad;
	protected int posicionX;
	protected int posicionY;
	protected ImageIcon imagen;
	
	protected void setImagen(){}
	
	protected void dibujarse(){}
	
	protected void moverse(){}
	
	protected void comer(Personaje personaje){}
	
	protected void morir(){}
}
