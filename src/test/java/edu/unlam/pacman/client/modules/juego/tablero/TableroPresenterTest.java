package edu.unlam.pacman.client.modules.juego.tablero;

import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.client.modules.BasePresenterTest;
import edu.unlam.pacman.client.modules.juego.personaje.pacman.Pacman;
import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.comunication.bus.events.async.MoveEventCallback;
import edu.unlam.pacman.shared.comunication.bus.events.async.MoveEventRequest;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 23:59
 */
public class TableroPresenterTest extends BasePresenterTest<TableroPresenter, TableroView> {
    @Test
    public void paintTablero() {
        // Given
        // When
        presenter.paint();

        // Then
        verify(view, Mockito.atLeast(1)).dibujarPared(any(Coordenada.class), anyInt(), anyInt());
    }

    @Test
    public void canMove() {
        // Given
        MoveEventRequest moveEvent = new MoveEventRequest("1", new Coordenada(25, 25), Direction.RIGHT, "personaje", new Pacman(new Jugador()));

        // When
        presenter.handleMoveEventRequest(moveEvent);

        // Then
        verify(presenter.getEventBus()).post(new MoveEventCallback(moveEvent.getSubject(), moveEvent.getOrigen(), moveEvent.getDireccion(), moveEvent.getPersonajeType(), moveEvent.getPersonaje()));
    }

    @Test
    public void canNotMove() {
        // Given
        MoveEventRequest moveEvent = new MoveEventRequest("1", new Coordenada(25, 25), Direction.LEFT, "personaje", new Pacman(new Jugador()));

        // When
        presenter.handleMoveEventRequest(moveEvent);

        // Then
        verify(presenter.getEventBus(), Mockito.never()).post(new MoveEventCallback(moveEvent.getSubject(), moveEvent.getOrigen(), moveEvent.getDireccion(), moveEvent.getPersonajeType(), moveEvent.getPersonaje()));
    }

    @Override
    protected TableroPresenter getPresenter() {
        return new TableroPresenter();
    }
}
