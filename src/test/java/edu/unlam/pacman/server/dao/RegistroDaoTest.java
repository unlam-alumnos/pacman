package edu.unlam.pacman.server.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.unlam.pacman.client.modules.login.login.Jugador;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 18:38
 */
public class RegistroDaoTest {

    private RegistroDao dao;

    @Before
    public void setup() {
        this.dao = RegistroDao.getInstance();
    }

    @Test
    public void register() {
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
}
