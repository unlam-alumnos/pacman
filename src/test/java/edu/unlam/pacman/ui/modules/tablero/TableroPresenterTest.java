package edu.unlam.pacman.ui.modules.tablero;

import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.common.Coordenada;
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

    @Override
    protected TableroPresenter getPresenter() {
        return new TableroPresenter();
    }
}
