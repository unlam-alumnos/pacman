package edu.unlam.pacman.client.modules.juego.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.client.modules.BasePresenterTest;
import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.client.modules.login.login.LoginPresenter;
import edu.unlam.pacman.client.modules.login.login.LoginView;
import edu.unlam.pacman.server.service.JugadorService;
import edu.unlam.pacman.shared.exception.ServiceException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 23:59
 */
public class LoginPresenterTest extends BasePresenterTest<LoginPresenter, LoginView> {

    private JugadorService jugadorService;

    @Before
    public void setup() {
        super.setup();
        this.jugadorService = Mockito.mock(JugadorService.class);
        super.presenter.setJugadorService(jugadorService);
    }

    @Test
    public void login() throws ServiceException {
        // Given
        String username = "pepo";
        String password = "pass";
        given(jugadorService.getByUsernameAndPassword(username, password)).willReturn(new Jugador(username, password));

        // When
        presenter.login();

        // Then
        verify(jugadorService).getByUsernameAndPassword(username, password);
        verify(view).clear();
    }

    @Test
    public void errorOnLogin() throws ServiceException {
        // Given
        String username = "   ";
        String password = "pass";
        String error = "Username no puede ser vacío. Tesing!";
        given(jugadorService.getByUsernameAndPassword(username, password)).willThrow(new ServiceException(error));

        // When
        presenter.login();

        // Then
        verify(jugadorService).getByUsernameAndPassword(username, password);
        verify(view).error(error);
        verify(view, Mockito.never()).clear();
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}
