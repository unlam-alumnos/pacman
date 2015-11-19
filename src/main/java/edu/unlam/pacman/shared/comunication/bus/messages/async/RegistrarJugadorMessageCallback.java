package edu.unlam.pacman.shared.comunication.bus.messages.async;

import edu.unlam.pacman.shared.comunication.bus.messages.BaseMessage;

public class RegistrarJugadorMessageCallback implements BaseMessage, AsyncMessage {
    private String sender;
    private boolean success;
    private String message;

    public RegistrarJugadorMessageCallback() {
    }

    public RegistrarJugadorMessageCallback(String sender, boolean success) {
        this(sender, success, null);
    }

    public RegistrarJugadorMessageCallback(String sender, boolean success, String message) {
        this.sender = sender;
        this.success = success;
        this.message = message;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
