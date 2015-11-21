package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.modules.juego.personaje.PersonajePresenter;
import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.comunication.bus.events.BlockEvent;
import edu.unlam.pacman.shared.comunication.bus.events.async.MoveEventRequest;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.JugadorActual;
import edu.unlam.pacman.shared.model.Status;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class FantasmaPresenter extends PersonajePresenter<FantasmaView> implements FantasmaView.MyView {
    public FantasmaPresenter(Jugador jugador, Coordenada origen, Color color) {
        super(new FantasmaView());
        super.personaje = new Fantasma(jugador, origen, color);
        super.personaje.setActive(JugadorActual.get().equals(jugador));
        initConstantMovement();
    }


    @Subscribe
    @AllowConcurrentEvents
    public void handleBlockEventCallback(BlockEvent e) {
        if (e.getSubject().equals(personaje.getJugador().getUsername())){
            personaje.setTimeBlock(new GregorianCalendar().getTimeInMillis());
            personaje.setStatus(Status.BLOCK);
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

    @Override
    public void move(Direction direction) {
        long diff = new GregorianCalendar().getTimeInMillis() - personaje.getTimeBlock();

        if (diff >= 1000){
            if (personaje != null && personaje.getJugador() != null && personaje.isActive()) {
                eventBus.post(new MoveEventRequest(personaje.getJugador().getUsername(), new Coordenada(personaje.getX(), personaje.getY()), direction, "pacman", personaje));
                personaje.setStatus(Status.NORMAL);
            }
        }
    }
}
