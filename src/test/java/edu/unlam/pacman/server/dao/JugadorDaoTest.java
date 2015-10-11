package edu.unlam.pacman.server.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.exception.ServiceException;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 18:38
 */
public class JugadorDaoTest {

    private JugadorDao dao;

    @Before
    public void setup() {
        this.dao = JugadorDao.getInstance();
    }

    @Test
    public void register() throws ServiceException {
        // Given
        String username = "testUsername7020";
        String password = "pass";
        Jugador jugador = new Jugador(username, password);

        // When
        dao.register(jugador);

        // Then
        Jugador registrado = dao.getByUsername(username);
        Assert.assertNotNull(registrado);
        Assert.assertEquals(username, registrado.getUsername());

        // Finally
        dao.unregister(registrado);
    }

    @Test
    public void getByUsernameAndPassword() throws ServiceException {
        // Given
        String username = "testUsername7020";
        String password = "pass";
        Jugador jugador = new Jugador(username, password);

        // When
        dao.register(jugador);

        // Then
        Jugador registrado = dao.getByUsernameAndPassword(username, password);
        Assert.assertNotNull(registrado);
        Assert.assertEquals(username, registrado.getUsername());
        Assert.assertEquals(password, registrado.getPassword());

        // Finally
        dao.unregister(registrado);
    }

    @Test
    public void getByUsernameAndPassword_nonExistingPlayer() throws ServiceException {
        // Given
        String username = "testUsername7020";
        String password = "pass";

        // When
        Jugador registrado = dao.getByUsernameAndPassword(username, password);
        Assert.assertNull(registrado);
    }
}
