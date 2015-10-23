package edu.unlam.pacman.client.modules.juego.personaje.pacman;

import edu.unlam.pacman.shared.model.Status;
import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.client.modules.BasePresenterTest;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 23:59
 */
public class FantasmaPresenterTest extends BasePresenterTest<PacmanPresenter, PacmanView> {
    @Test
    public void paintPacman() {
        // Given
        // When
        presenter.paintPacman();

        // Then
        verify(view, Mockito.atLeast(1)).paintPacman(anyInt(), anyInt(), anyInt(), anyInt(), any(Direction.class), 1, any(Status.class));
    }

    @Override
    protected PacmanPresenter getPresenter() {
        return new PacmanPresenter();
    }
}
