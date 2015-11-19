package edu.unlam.pacman.client.modules.menu.menu;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.comunication.bus.events.StartEvent;
import edu.unlam.pacman.shared.comunication.bus.events.async.ClientEventCallback;
import edu.unlam.pacman.shared.comunication.bus.messages.JugadorMessage;
import edu.unlam.pacman.shared.comunication.bus.messages.StartMessage;
import edu.unlam.pacman.shared.model.JugadorActual;
import edu.unlam.pacman.shared.util.PropertiesUtils;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class MenuPresenter extends Presenter<MenuView> implements MenuView.MyView {
    private LinkedHashSet<Jugador> jugadores;

    public MenuPresenter() {
        super(new MenuView());
        this.jugadores = new LinkedHashSet<>();
    }

    @Override
    public void crearPartida(String ipServer, int portServer) {
        // TODO: Que hacemos? La partida ya la crea el server cuando inicia la app al levantar el socket
        // eventBus.post(new ServerEvent(ipServer, portServer));
    }

    @Override
    public void unirseAPartida(String ipServer, int portServer) {
        // eventBus.post(new ClientEventRequest(ipServer,portServer));
        if ("true".equals(PropertiesUtils.pref().get(SharedConstants.GAME_SERVER))) {
            jugadores.add(JugadorActual.get());
        } else {
            communicationHandler.send(new JugadorMessage(JugadorActual.get()), JugadorMessage.class);
        }
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

    private void start(LinkedHashSet<Jugador> jugadores) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("jugadores", jugadores);
        eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.JUEGO, parameters));
    }

    @Subscribe
    public void handleClientCallbackEvent(ClientEventCallback event) {

    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleJugadorMessage(JugadorMessage jugadorMessage) {
        Jugador jugador = jugadorMessage.getJugador();
        jugadores.add(jugador);
    }
}
