package edu.unlam.pacman.client.modules.juego.pacman;

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
public class PacmanPresenter extends Presenter<PacmanView> implements PacmanView.MyView {
    private Pacman pacman;
    private String animacionBoca;
    private short turnoBoca=1;

    public PacmanPresenter() {
        super(new PacmanView());
        this.pacman = new Pacman();
        this.pacman.setActive(true);
        initConstantMovement();
    }

    @Override
    public void move(Direction direction) {
        if (pacman.isActive()) {
            eventBus.post(new Request<>(new MoveEvent(pacman.getId(), new Coordenada(pacman.getX(), pacman.getY()), direction)));
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleMoveEventCallback(Callback<MoveEvent> callback) {
        MoveEvent moveEvent = callback.getEvent();
        if (pacman.getId().equals(moveEvent.getSubject())) {
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
                default:
                    break;
            }
            pacman.setX(moveEvent.getOrigen().getX() + x);
            pacman.setY(moveEvent.getOrigen().getY() + y);
            pacman.setDirection(direction);
        }
    }

    @Override
    public void paintPacman() {
        getView().paintPacman(pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight(), pacman.getDirection(), animacionBoca);
        getView().repaint();
    }

    /**
    * Inicia el Timer que movera constantemente al pacman, en la direccion seteada.
    */

    private void initConstantMovement(){
        ActionListener animate = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                move(pacman.getDirection());
                if (turnoBoca == 1){
                    animacionBoca = "uno";
                    turnoBoca++;
                }else if(turnoBoca == 2){
                    animacionBoca = "dos";
                    turnoBoca++;
                }else if(turnoBoca == 3){
                    animacionBoca = "tres";
                    turnoBoca++;
                }else{
                    animacionBoca = "cero";
                    turnoBoca=1;
                }
            }
        };
        Timer timer = new Timer(pacman.getSpeed(), animate);
        timer.start();
    }
}
