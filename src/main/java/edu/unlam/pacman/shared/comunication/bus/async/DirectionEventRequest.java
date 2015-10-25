package edu.unlam.pacman.shared.comunication.bus.async;

import edu.unlam.pacman.shared.comunication.bus.events.DirectionEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;

/**
 * @author Cristian Miranda
 * @since 10/25/15 - 16:00
 */
public class DirectionEventRequest extends DirectionEvent {
    public DirectionEventRequest(String subject, Coordenada origen, Direction direccion) {
        super(subject, origen, direccion);
    }
}
