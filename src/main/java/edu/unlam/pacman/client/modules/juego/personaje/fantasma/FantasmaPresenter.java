package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.modules.juego.personaje.PersonajePresenter;
import edu.unlam.pacman.shared.comunication.bus.events.BlockEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    @Subscribe
    @AllowConcurrentEvents
    public void handleDeadEventCallback(BlockEvent e) {
        if (e.getSubject().equals(personaje.getId())){
            personaje.setActive(e.isActive());
            personaje.setStatus(e.getStatus());
        }
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
