package edu.unlam.pacman.server.dao;

import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.shared.exception.ServiceException;
import fi.evident.dalesbred.NonUniqueResultException;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 17:47
 */
public class JugadorDao {
    private static JugadorDao instance;

    private JugadorDao() {
    }

    public static JugadorDao getInstance() {
        if (instance == null) {
            instance = new JugadorDao();
        }
        return instance;
    }

    public Jugador getByUsername(String username) throws ServiceException {
        Jugador jugador = null;
        try {
            jugador = DaoUtils.get().findUnique(Jugador.class, "SELECT * FROM JUGADORES WHERE USERNAME = ?", username);
        } catch (NonUniqueResultException e) {
            if (!e.getMessage().contains("Expected unique result but got 0 rows")) {
                throw new ServiceException("Más de un jugador encontrado para username: " + username);
            }
        }
        return jugador;
    }

    public Jugador getByUsernameAndPassword(String username, String password) throws ServiceException {
        Jugador jugador = null;
        try {
            jugador = DaoUtils.get().findUnique(Jugador.class, "SELECT * FROM JUGADORES WHERE USERNAME = ? AND PASSWORD = ?", username, password);
        } catch (NonUniqueResultException e) {
            if (!e.getMessage().contains("Expected unique result but got 0 rows")) {
                throw new ServiceException("Más de un jugador encontrado para username: " + username + " y password: *******");
            }
        }
        return jugador;
    }

    public Long register(Jugador jugador) throws ServiceException {
        DaoUtils.get().update("INSERT INTO JUGADORES (USERNAME, PASSWORD) VALUES (?, ?)", jugador.getUsername(), jugador.getPassword());
        return getByUsername(jugador.getUsername()).getId();
    }

    public void unregister(Jugador jugador) {
        DaoUtils.get().update("DELETE FROM JUGADORES WHERE USERNAME = ?", jugador.getUsername());
    }
}
