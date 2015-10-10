package edu.unlam.pacman.ui.modules.tablero;

import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.common.Coordenada;
import edu.unlam.pacman.common.Direction;
import edu.unlam.pacman.comunication.bus.async.Callback;
import edu.unlam.pacman.comunication.bus.async.Request;
import edu.unlam.pacman.comunication.bus.events.MoveEvent;
import edu.unlam.pacman.ui.modules.BasePresenterTest;

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
