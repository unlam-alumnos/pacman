package edu.unlam.pacman.client.communication;

import edu.unlam.pacman.server.service.CommunicationService;
import edu.unlam.pacman.shared.comunication.bus.Bus;
import edu.unlam.pacman.shared.comunication.bus.events.MessageEvent;

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

    public void send(String message) {
        eventBus.post(new MessageEvent(message));
    }
}
