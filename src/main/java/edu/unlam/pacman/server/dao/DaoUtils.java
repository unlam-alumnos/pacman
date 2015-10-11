package edu.unlam.pacman.server.dao;

import fi.evident.dalesbred.Database;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 17:30
 */
public class DaoUtils {

    private static Database db;
    private static DaoUtils instance;

    private DaoUtils() {
        // TODO: Sacar credenciales a un properties file
        this.db = Database.forUrlAndCredentials("jdbc:mysql://crismiranda.net:3336/PACMAN", "root", "");
    }

    public Database get() {
        if (instance == null) {
            instance = new DaoUtils();
        }
        return db;
    }
}
