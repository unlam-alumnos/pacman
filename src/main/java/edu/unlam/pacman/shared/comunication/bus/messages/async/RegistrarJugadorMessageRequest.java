package edu.unlam.pacman.shared.comunication.bus.messages.async;

import edu.unlam.pacman.shared.comunication.bus.messages.BaseMessage;

public class RegistrarJugadorMessageRequest implements BaseMessage, AsyncMessage {
    private String sender;
    private String username;
    private String password;
    private String passwordConfirmation;

    public RegistrarJugadorMessageRequest() {
    }

    public RegistrarJugadorMessageRequest(String username, String password, String passwordConfirmation) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
}
