package edu.unlam.pacman.client.communication;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.server.service.CommunicationService;
import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.comunication.bus.Bus;
import edu.unlam.pacman.shared.comunication.bus.events.async.ClientEventCallback;
import edu.unlam.pacman.shared.comunication.bus.events.async.ClientEventRequest;
import edu.unlam.pacman.shared.comunication.bus.events.ServerEvent;
import edu.unlam.pacman.shared.comunication.bus.messages.BaseMessage;
import edu.unlam.pacman.shared.comunication.bus.messages.GameMessage;
import edu.unlam.pacman.shared.comunication.bus.messages.async.AsyncMessage;
import edu.unlam.pacman.shared.model.JugadorActual;
import edu.unlam.pacman.shared.util.PropertiesUtils;

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
        if (message instanceof AsyncMessage) {
            AsyncMessage asyncMessage = (AsyncMessage) message;
            if (asyncMessage.getSender() == null) {
                asyncMessage.setSender(PropertiesUtils.pref().get(SharedConstants.CLIENT_ID));
            }
            if ("true".equals(PropertiesUtils.pref().get(SharedConstants.GAME_SERVER))
                    && asyncMessage.getSender().equals(PropertiesUtils.pref().get(SharedConstants.CLIENT_ID))) {
                eventBus.post(asyncMessage);
            } else {
                eventBus.post(new GameMessage(currentPlayer, type.cast(asyncMessage), type.getName()));
            }
        } else {
            eventBus.post(new GameMessage(currentPlayer, message, type.getName()));
        }
    }
}
