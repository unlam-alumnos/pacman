package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.modules.juego.personaje.PersonajePresenter;
import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.comunication.bus.events.BlockEvent;
import edu.unlam.pacman.shared.model.JugadorActual;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class FantasmaPresenter extends PersonajePresenter<FantasmaView> implements FantasmaView.MyView {
    public FantasmaPresenter(Jugador jugador) {
        super(new FantasmaView());
        super.personaje = new Fantasma(jugador);
        super.personaje.setActive(JugadorActual.get().equals(jugador));
        initConstantMovement();
    }


    @Subscribe
    @AllowConcurrentEvents
    public void handleDeadEventCallback(BlockEvent e) {
        if (e.getSubject().equals(personaje.getJugador().getUsername())){
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
