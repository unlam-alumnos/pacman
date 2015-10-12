package edu.unlam.pacman.client.modules.juego.pacman;

import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.shared.Direction;
import edu.unlam.pacman.client.modules.BasePresenterTest;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 23:59
 */
public class PacmanPresenterTest extends BasePresenterTest<PacmanPresenter, PacmanView> {
    @Test
    public void paintPacman() {
        // Given
        // When
        presenter.paintPacman();

        // Then
        verify(view, Mockito.atLeast(1)).paintPacman(anyInt(), anyInt(), anyInt(), anyInt(), any(Direction.class));
    }

    @Override
    protected PacmanPresenter getPresenter() {
        return new PacmanPresenter();
    }
}
