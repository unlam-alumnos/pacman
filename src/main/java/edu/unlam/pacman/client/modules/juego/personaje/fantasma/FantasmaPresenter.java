package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import edu.unlam.pacman.client.modules.juego.personaje.PersonajePresenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class FantasmaPresenter extends PersonajePresenter<FantasmaView> implements FantasmaView.MyView {
    public FantasmaPresenter() {
        super(new FantasmaView());
        super.personaje = new Fantasma();
        super.personaje.setActive(false);
        initConstantMovement();
    }

    /**
    * Inicia el Timer que movera constantemente al pacman, en la direccion seteada.
    */
    private void initConstantMovement(){
        ActionListener animate = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                move(personaje.getDirection());
            }
        };
        Timer timer = new Timer(personaje.getSpeed(), animate);
        timer.start();
    }
}
