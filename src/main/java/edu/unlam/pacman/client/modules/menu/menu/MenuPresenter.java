package edu.unlam.pacman.client.modules.menu.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.async.ClientEventCallback;
import edu.unlam.pacman.shared.comunication.bus.async.ClientEventRequest;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ServerEvent;
import edu.unlam.pacman.shared.comunication.bus.events.StartEvent;
import edu.unlam.pacman.shared.comunication.bus.messages.JugadorMessage;
import edu.unlam.pacman.shared.comunication.bus.messages.StartMessage;
import edu.unlam.pacman.shared.model.JugadorActual;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class MenuPresenter extends Presenter<MenuView> implements MenuView.MyView {
    private List<Jugador> jugadores;

    public MenuPresenter() {
        super(new MenuView());
        this.jugadores = new ArrayList<>();
    }

    @Override
    public void crearPartida(String ipServer, int portServer) {
        eventBus.post(new ServerEvent(ipServer, portServer));
    }

    @Override
    public void unirseAPartida(String ipServer, int portServer) {
        eventBus.post(new ClientEventRequest(ipServer,portServer));
    }

    @Override
    public void empezarPartida(){
        communicationHandler.send(new StartMessage(jugadores), StartMessage.class);
        eventBus.post(new StartEvent());
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleStartEventCallback(StartEvent startMessage){
        start(jugadores);
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleStartMessage(StartMessage startMessage) {
        start(startMessage.getJugadores());
    }

    private void start(List<Jugador> jugadores) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("jugadores", jugadores);
        eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.JUEGO, parameters));
    }

    @Subscribe
    public void handleClientCallbackEvent(ClientEventCallback event) {
        communicationHandler.send(new JugadorMessage(JugadorActual.get()), JugadorMessage.class);
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleJugadorMessage(JugadorMessage jugadorMessage) {
        Jugador jugador = jugadorMessage.getJugador();
        jugadores.add(jugador);
    }
}
