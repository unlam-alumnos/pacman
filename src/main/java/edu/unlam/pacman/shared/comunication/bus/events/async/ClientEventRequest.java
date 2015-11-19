package edu.unlam.pacman.shared.comunication.bus.events.async;

import edu.unlam.pacman.shared.comunication.bus.events.GameEvent;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class ClientEventRequest extends GameEvent {
    private String ipServer;
    private int portServer;

    public ClientEventRequest(String ipServer, int portServer) {
        this.ipServer = ipServer;
        this.portServer = portServer;
    }

    public String getIpServer() {
        return ipServer;
    }

    public int getPortServer() {
        return portServer;
    }
}
