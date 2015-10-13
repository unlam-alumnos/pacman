package edu.unlam.pacman.client.modules.juego.tablero;

import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.client.modules.BasePresenterTest;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.comunication.bus.async.Callback;
import edu.unlam.pacman.shared.comunication.bus.async.Request;
import edu.unlam.pacman.shared.comunication.bus.events.MoveEvent;

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
        MoveEvent moveEvent = new MoveEvent("1", new Coordenada(50, 50), Direction.RIGHT);
        Request<MoveEvent> request = new Request<>(moveEvent);

        // When
        presenter.handleMoveEventRequest(request);

        // Then
        verify(presenter.getEventBus()).post(new Callback<>(moveEvent));
    }

    @Test
    public void canNotMove() {
        // Given
        MoveEvent moveEvent = new MoveEvent("1", new Coordenada(50, 50), Direction.LEFT);
        Request<MoveEvent> request = new Request<>(moveEvent);

        // When
        presenter.handleMoveEventRequest(request);

        // Then
        verify(presenter.getEventBus(), Mockito.never()).post(new Callback<>(moveEvent));
    }

    @Override
    protected TableroPresenter getPresenter() {
        return new TableroPresenter();
    }
}
