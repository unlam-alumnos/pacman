package edu.unlam.pacman.client.modules.juego.personaje.pacman;

import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.client.modules.BasePresenterTest;
import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.modules.login.login.Jugador;

import static org.mockito.Matchers.any;
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
        presenter.paintPersonaje();

        // Then
        verify(view, Mockito.atLeast(1)).paintPersonaje(any(Personaje.class));
    }

    @Override
    protected PacmanPresenter getPresenter() {
        return new PacmanPresenter(new Jugador());
    }
}
