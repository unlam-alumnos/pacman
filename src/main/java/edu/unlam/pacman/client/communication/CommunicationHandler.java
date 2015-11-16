package edu.unlam.pacman.client.communication;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.server.service.CommunicationService;
import edu.unlam.pacman.shared.comunication.bus.Bus;
import edu.unlam.pacman.shared.comunication.bus.async.ClientEventCallback;
import edu.unlam.pacman.shared.comunication.bus.async.ClientEventRequest;
import edu.unlam.pacman.shared.comunication.bus.events.ServerEvent;
import edu.unlam.pacman.shared.comunication.bus.messages.BaseMessage;
import edu.unlam.pacman.shared.comunication.bus.messages.GameMessage;
import edu.unlam.pacman.shared.model.JugadorActual;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class CommunicationHandler {
    private static CommunicationHandler instance;

    private Bus eventBus;
    private CommunicationService communicationService;

    private CommunicationHandler() {
        this.eventBus = Bus.getInstance();
        this.eventBus.register(this);
        this.communicationService = CommunicationService.getInstance();
    }

    public static CommunicationHandler getInstance() {
        if (instance == null) {
            instance = new CommunicationHandler();
        }
        return instance;
    }

    @Subscribe
    public void handleServerEvent(ServerEvent event) {
        communicationService.initServer(event.getPortServer());
    }

    @Subscribe
    public void handleClientRequestEvent(ClientEventRequest event) {
        communicationService.initClient(event.getIpServer(), event.getPortServer());
        eventBus.post(new ClientEventCallback());
    }

    public <T extends BaseMessage> void send(BaseMessage message, Class<T> type) {
        Jugador currentPlayer = JugadorActual.get();
        eventBus.post(new GameMessage(currentPlayer, message, type.getName()));
    }
}
