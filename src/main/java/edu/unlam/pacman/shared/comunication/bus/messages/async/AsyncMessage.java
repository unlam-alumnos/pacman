package edu.unlam.pacman.shared.comunication.bus.messages.async;

import edu.unlam.pacman.shared.comunication.bus.messages.BaseMessage;

public interface AsyncMessage extends BaseMessage{
    String getSender();
    void setSender(String sender);
}
