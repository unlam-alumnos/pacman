package edu.unlam.pacman.server.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import fi.evident.dalesbred.Database;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 17:06
 */
public class DaoUtilsTest {

    @Test
    public void testConnection() {
        Database db = Database.forUrlAndCredentials("jdbc:mysql://crismiranda.net:3306/PACMAN", "root", "");
        List<Jugador> jugadores = db.findAll(Jugador.class, "SELECT * FROM JUGADORES");
        Assert.assertNotNull(jugadores);
        Assert.assertEquals(1, jugadores.size());
    }

}
