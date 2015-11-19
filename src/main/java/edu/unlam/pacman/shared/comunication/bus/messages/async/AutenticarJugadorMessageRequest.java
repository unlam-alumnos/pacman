package edu.unlam.pacman.shared.comunication.bus.messages.async;

import edu.unlam.pacman.shared.comunication.bus.messages.BaseMessage;

public class AutenticarJugadorMessageRequest implements BaseMessage, AsyncMessage {
    private String sender;
    private String username;
    private String password;

    public AutenticarJugadorMessageRequest() {
    }

    public AutenticarJugadorMessageRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }
}
