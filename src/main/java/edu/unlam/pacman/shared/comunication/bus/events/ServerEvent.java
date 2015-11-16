package edu.unlam.pacman.shared.comunication.bus.events;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class ServerEvent extends GameEvent {
    private String ipServer;
    private int portServer;

    public ServerEvent(String ipServer, int portServer) {
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
