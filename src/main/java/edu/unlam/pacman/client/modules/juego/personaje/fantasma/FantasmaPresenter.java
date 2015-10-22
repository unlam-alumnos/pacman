package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.async.Callback;
import edu.unlam.pacman.shared.comunication.bus.async.Request;
import edu.unlam.pacman.shared.comunication.bus.events.MoveEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class FantasmaPresenter extends Presenter<FantasmaView> implements FantasmaView.MyView {
    private Fantasma fantasma;

    public FantasmaPresenter() {
        super(new FantasmaView());
        this.fantasma = new Fantasma();
        this.fantasma.setActive(true);
        initConstantMovement();
    }

    @Override
    public void move(Direction direction) {
        if (fantasma.isActive()) {
            eventBus.post(new Request<>(new MoveEvent(fantasma.getId(), new Coordenada(fantasma.getX(), fantasma.getY()), direction)));
        }
    }

    @Override
    public void changeDirection(Direction direction){
        if (fantasma.isActive()) {
            fantasma.setDirection(direction);
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleMoveEventCallback(Callback<MoveEvent> callback) {
        MoveEvent moveEvent = callback.getEvent();
        if (fantasma.getId().equals(moveEvent.getSubject())) {
            int x = 0;
            int y = 0;
            Direction direction = moveEvent.getDireccion();
            int offset = 50;
            switch (direction) {
                case UP:
                    x = 0;
                    y = -offset;
                    break;
                case DOWN:
                    x = 0;
                    y = offset;
                    break;
                case LEFT:
                    x = -offset;
                    y = 0;
                    break;
                case RIGHT:
                    x = offset;
                    y = 0;
                    break;
                case NONE:
                    x = 0;
                    y = 0;
                    break;
                default:
                    break;
            }
            fantasma.setX(moveEvent.getOrigen().getX() + x);
            fantasma.setY(moveEvent.getOrigen().getY() + y);
            fantasma.setDirection(direction);
        }
    }

    @Override
    public void paintFantasma() {
        getView().paintFantasma(fantasma.getX(), fantasma.getY(), fantasma.getWidth(), fantasma.getHeight(), fantasma.getDirection());
        getView().repaint();
    }

    /**
    * Inicia el Timer que movera constantemente al pacman, en la direccion seteada.
    */

    private void initConstantMovement(){
        ActionListener animate = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                move(fantasma.getDirection());
            }
        };
        Timer timer = new Timer(fantasma.getSpeed(), animate);
        timer.start();
    }
}
