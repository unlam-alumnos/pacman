package edu.unlam.pacman.shared.model;

import edu.unlam.pacman.client.modules.login.login.Jugador;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 19:30
 */
public class JugadorActual {
    private static Jugador jugador;
    private static JugadorActual instance;

    private JugadorActual() {
    }

    private static JugadorActual init() {
        if (instance == null) {
            instance = new JugadorActual();
        }
        return instance;
    }

    public static Jugador get() {
        init();
        return jugador;
    }

    public static void set(Jugador actual) {
        init();
        jugador = actual;
    }
}
