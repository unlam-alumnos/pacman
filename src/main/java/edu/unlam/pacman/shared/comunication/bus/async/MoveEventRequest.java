package edu.unlam.pacman.shared.comunication.bus.async;

import edu.unlam.pacman.shared.comunication.bus.events.MoveEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;

/**
 * @author Cristian Miranda
 * @since 10/25/15 - 16:00
 */
public class MoveEventRequest extends MoveEvent {
    public MoveEventRequest(String subject, Coordenada origen, Direction direccion, String tipo) {
        super(subject, origen, direccion, tipo);
    }
}
