package edu.unlam.pacman.server.dao;

import edu.unlam.pacman.client.modules.login.login.Jugador;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 17:47
 */
public class RegistroDao {
    private static RegistroDao instance;

    private RegistroDao() {
    }

    public static RegistroDao getInstance() {
        if (instance == null) {
            instance = new RegistroDao();
        }
        return instance;
    }

    public Jugador getByUsername(String username) {
        return DaoUtils.get().findUnique(Jugador.class, "SELECT * FROM JUGADORES WHERE USERNAME = ?", username);
    }

    public Long register(Jugador jugador) {
        DaoUtils.get().update("INSERT INTO JUGADORES (USERNAME, PASSWORD) VALUES (?, ?)", jugador.getUsername(), jugador.getPassword());
        return getByUsername(jugador.getUsername()).getId();
    }

    public void unregister(Jugador jugador) {
        DaoUtils.get().update("DELETE FROM JUGADORES WHERE USERNAME = ?", jugador.getUsername());
    }
}
