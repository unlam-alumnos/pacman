package edu.unlam.pacman.server.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.server.dao.RegistroDao;
import edu.unlam.pacman.shared.exception.ServiceException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 18:26
 */
public class RegistroServiceTest {

    private RegistroService service;
    private RegistroDao registroDao;

    @Before
    public void setup() {
        this.service = RegistroService.getInstance();
        this.registroDao = Mockito.mock(RegistroDao.class);
        this.service.setDao(registroDao);
    }

    @Test
    public void usernameVacio() {
        // Given
        String username = "   ";
        String password = "";
        String passwordConfirmation = "";

        // When
        try {
            service.register(username, password, passwordConfirmation);
        } catch (ServiceException e) {
            // Then
            Assert.assertEquals("El nombre de usuario no puede ser vacío", e.getMessage());
        }
    }

    @Test
    public void passwordVacia() {
        // Given
        String username = "pepo";
        String password = "";
        String passwordConfirmation = "";

        // When
        try {
            service.register(username, password, passwordConfirmation);
        } catch (ServiceException e) {
            // Then
            Assert.assertEquals("La contraseña no puede ser vacía", e.getMessage());
        }
    }

    @Test
    public void passwordsNoIdenticas() {
        // Given
        String username = "pepo";
        String password = "pass";
        String passwordConfirmation = "noIgual";

        // When
        try {
            service.register(username, password, passwordConfirmation);
        } catch (ServiceException e) {
            // Then
            Assert.assertEquals("Las contraseñas deben ser identicas", e.getMessage());
        }
    }

    @Test
    public void usernameYaRegistrado() {
        // Given
        String username = "pepo";
        String password = "pass";
        String passwordConfirmation = "pass";
        given(registroDao.getByUsername(username)).willReturn(new Jugador(username, password));

        // When
        try {
            service.register(username, password, passwordConfirmation);
        } catch (ServiceException e) {
            // Then
            Assert.assertEquals("Ya existe un jugador con ese nombre", e.getMessage());
        }
    }

    @Test
    public void registroExitoso() throws ServiceException {
        // Given
        String username = "pepo";
        String password = "pass";
        String passwordConfirmation = "pass";

        // When
        service.register(username, password, passwordConfirmation);

        // Then
        verify(registroDao).register(new Jugador(username, password));
    }
}
