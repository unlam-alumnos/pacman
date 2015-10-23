package edu.unlam.pacman.client.modules.juego.personaje.pacman;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.async.Callback;
import edu.unlam.pacman.shared.comunication.bus.async.Request;
import edu.unlam.pacman.shared.comunication.bus.events.HunterEvent;
import edu.unlam.pacman.shared.comunication.bus.events.KeyEvent;
import edu.unlam.pacman.shared.comunication.bus.events.MoveEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class PacmanPresenter extends Presenter<PacmanView> implements PacmanView.MyView {
    private Pacman pacman;

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

    @Override
    public void changeDirection(Direction direction){
        if (pacman.isActive()) {
            pacman.setDirection(direction);
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleKeyEventCallback(HunterEvent e) {
        if (!e.getSubject().equals(pacman.getId())){
            pacman.setStatus(Status.VICTIM);
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleKeyEventCallback(KeyEvent e) {
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            changeDirection(Direction.UP);
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
            changeDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            changeDirection(Direction.LEFT);
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            changeDirection(Direction.DOWN);
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
                case NONE:
                    x = 0;
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
        getView().paintPacman(pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight(), pacman.getDirection(), pacman.getImageIndex(), pacman.getStatus());
        getView().repaint();
    }

    /**
    * Inicia el Timer que movera constantemente al pacman, en la direccion seteada.
    */

    private void initConstantMovement(){
        ActionListener animate = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
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
        Timer timer = new Timer(pacman.getSpeed(), animate);
        timer.start();
    }
}
