package edu.unlam.pacman.client.modules.juego.registro;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.client.modules.BasePresenterTest;
import edu.unlam.pacman.client.modules.registro.registro.RegistroPresenter;
import edu.unlam.pacman.client.modules.registro.registro.RegistroView;
import edu.unlam.pacman.server.service.RegistroService;
import edu.unlam.pacman.shared.exception.ServiceException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 23:59
 */
public class RegistroPresenterTest extends BasePresenterTest<RegistroPresenter, RegistroView> {

    private RegistroService registroService;

    @Before
    public void setup() {
        super.setup();
        this.registroService = Mockito.mock(RegistroService.class);
        super.presenter.setService(registroService);
    }

    @Test
    public void register() throws ServiceException {
        // Given
        String username = "pepo";
        String password = "pass";
        String passwordConfirmation = "pass";

        // When
        presenter.register(username, password, passwordConfirmation);

        // Then
        verify(registroService).register(username, password, passwordConfirmation);
        verify(view).info("Jugador registrado con éxito");
        verify(view).clear();
    }

    @Test
    public void errorOnRegister() throws ServiceException {
        // Given
        String username = "";
        String password = "pass";
        String passwordConfirmation = "pass";
        String error = "Username no puede ser vacío. Tesing!";
        given(registroService.register(username, password, passwordConfirmation)).willThrow(new ServiceException(error));

        // When
        presenter.register(username, password, passwordConfirmation);

        // Then
        verify(registroService).register(username, password, passwordConfirmation);
        verify(view).error("( ! ) - " + error);
        verify(view, Mockito.never()).clear();
    }

    @Override
    protected RegistroPresenter getPresenter() {
        return new RegistroPresenter();
    }
}
