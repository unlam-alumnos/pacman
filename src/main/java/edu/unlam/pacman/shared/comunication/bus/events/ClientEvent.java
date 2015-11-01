package edu.unlam.pacman.shared.comunication.bus.events;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class ClientEvent extends GameEvent {
    private String ipServer;
    private int portServer;

    public ClientEvent(String ipServer, int portServer) {
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
