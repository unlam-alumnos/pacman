package edu.unlam.pacman.shared.comunication.bus.events;

import edu.unlam.pacman.shared.model.Status;

/**
 * Created by gmartin on 30/10/2015.
 */
public class BlockEvent extends GameEvent {
    private boolean active;
    private Status status;

    public BlockEvent(String subject, boolean active, Status status){
        super(subject);
        this.active = active;
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public Status getStatus() {
        return status;
    }
}
