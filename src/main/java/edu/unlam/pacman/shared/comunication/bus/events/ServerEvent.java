package edu.unlam.pacman.shared.comunication.bus.events;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:46
 */
public class ServerEvent extends GameEvent {
    private int port;

    public ServerEvent(int port){
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
