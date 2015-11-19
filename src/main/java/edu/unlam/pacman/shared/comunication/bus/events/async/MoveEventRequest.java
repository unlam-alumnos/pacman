package edu.unlam.pacman.shared.comunication.bus.events.async;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.shared.comunication.bus.events.MoveEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;

/**
 * @author Cristian Miranda
 * @since 10/25/15 - 16:00
 */
public class MoveEventRequest extends MoveEvent {
    public MoveEventRequest(String subject, Coordenada origen, Direction direccion, String tipo, Personaje personaje) {
        super(subject, origen, direccion, tipo, personaje);
    }
}
