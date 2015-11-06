package edu.unlam.pacman.client.modules.juego.personaje.pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import edu.unlam.pacman.client.modules.juego.personaje.PersonajePresenter;
import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.model.JugadorActual;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class PacmanPresenter extends PersonajePresenter<PacmanView> implements PacmanView.MyView {
    public PacmanPresenter(Jugador jugador) {
        super(new PacmanView());
        super.personaje = new Pacman(jugador);
        super.personaje.setActive(JugadorActual.get().equals(jugador));
        initConstantMovement();
    }

    /**
    * Inicia el Timer que movera constantemente al pacman, en la direccion seteada.
    */
    private void initConstantMovement(){
        ActionListener animate = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Pacman pacman = (Pacman) personaje;
                move(pacman.getDirection());
                if (pacman.getImageIndex() == 1){
                    pacman.addImageIndex();
                }else if(pacman.getImageIndex() == 2){
                    pacman.addImageIndex();
                }else if(pacman.getImageIndex() == 3){
                    pacman.addImageIndex();
                }else if(pacman.getImageIndex() == 4){
                    pacman.addImageIndex();
                }else if(pacman.getImageIndex() == 5){
                    pacman.addImageIndex();
                }else{
                    pacman.setImageIndex((short)1);
                }
            }
        };
        Timer timer = new Timer(personaje.getSpeed(), animate);
        timer.start();
    }
}
