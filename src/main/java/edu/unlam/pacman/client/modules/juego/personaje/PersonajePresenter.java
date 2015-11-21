package edu.unlam.pacman.client.modules.juego.personaje;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.events.async.DirectionEventCallback;
import edu.unlam.pacman.shared.comunication.bus.events.async.DirectionEventRequest;
import edu.unlam.pacman.shared.comunication.bus.events.async.MoveEventCallback;
import edu.unlam.pacman.shared.comunication.bus.events.async.MoveEventRequest;
import edu.unlam.pacman.shared.comunication.bus.events.DeadEvent;
import edu.unlam.pacman.shared.comunication.bus.events.HunterEvent;
import edu.unlam.pacman.shared.comunication.bus.events.KeyEvent;
import edu.unlam.pacman.shared.comunication.bus.events.KillEvent;
import edu.unlam.pacman.shared.comunication.bus.events.PaintEvent;
import edu.unlam.pacman.shared.comunication.bus.messages.HunterMessage;
import edu.unlam.pacman.shared.comunication.bus.messages.MovementMessage;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public abstract class PersonajePresenter<V extends PersonajeView<?>> extends Presenter<V> implements PersonajeView.MyView {
    protected Personaje personaje;

    protected PersonajePresenter(V view) {
        super(view);
    }

    @Override
    public void move(Direction direction) {
        if (personaje != null && personaje.getJugador() != null && personaje.isActive()) {
            eventBus.post(new MoveEventRequest(personaje.getJugador().getUsername(), new Coordenada(personaje.getX(), personaje.getY()), direction, "pacman", personaje));
        }
    }

    @Override
    public void changeDirection(Direction direction) {
        if (personaje.isActive()) {
            eventBus.post(new DirectionEventRequest(personaje.getJugador().getUsername(), new Coordenada(personaje.getX(), personaje.getY()), direction));
        }
    }

    @Override
    public void paintPersonaje() {
        eventBus.post(new PaintEvent(personaje));
        getView().paintPersonaje(personaje);
        getView().repaint();
    }


    @Subscribe
    @AllowConcurrentEvents
    public void handleKillEventCallback(KillEvent e) {
        if (e.getSubject().equals(personaje.getJugador().getUsername())) {
            personaje.increaseKill();
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleDeadEventCallback(DeadEvent e) {
        if (e.getSubject().equals(personaje.getJugador().getUsername())) {
            personaje.dead(e.getCoordenada());
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleDirectionEventCallback(DirectionEventCallback directionEventCallback) {
        if (directionEventCallback.getSubject().equals(personaje.getJugador().getUsername())) {
            personaje.setDirection(directionEventCallback.getDireccion());
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleHunterEventCallback(HunterEvent e) {
        updateHunter(e.getSubject());
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleHunterMessageCallback(HunterMessage hunterMessage) {
        updateHunter(hunterMessage.getJugador().getUsername());
    }

    private void updateHunter(String jugadorUsername) {
        if (!jugadorUsername.equals(personaje.getJugador().getUsername())) {
            personaje.setStatus(Status.VICTIM);
        } else {
            personaje.setStatus(Status.HUNTER);
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
    public void handleMoveEventCallback(MoveEventCallback moveEvent) {
        if (personaje.getJugador().getUsername().equals(moveEvent.getSubject())) {
            int x = 0;
            int y = 0;
            Direction direction = moveEvent.getDireccion();
            int offset = 25;
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
            personaje.setX(moveEvent.getOrigen().getX() + x);
            personaje.setY(moveEvent.getOrigen().getY() + y);
            personaje.setDirection(direction);
            communicationHandler.send(new MovementMessage(personaje), MovementMessage.class);
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleMoveMessage(MovementMessage moveMessage) {
        // TODO: Identificar personajes
        if (moveMessage != null
                && moveMessage.getPersonaje() != null
                && personaje.getJugador().equals(moveMessage.getPersonaje().getJugador())) {
            personaje.setX(moveMessage.getPersonaje().getX());
            personaje.setY(moveMessage.getPersonaje().getY());
            personaje.setDirection(moveMessage.getPersonaje().getDirection());
        }
    }
}
