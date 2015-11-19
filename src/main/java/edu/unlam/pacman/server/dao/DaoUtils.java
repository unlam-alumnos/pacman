package edu.unlam.pacman.server.dao;

import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.util.PropertiesUtils;
import fi.evident.dalesbred.Database;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 17:30
 */
public class DaoUtils {

    private static Database db;
    private static DaoUtils instance;

    private DaoUtils() {
        this.db = Database.forUrlAndCredentials(
                PropertiesUtils.pref().get(SharedConstants.DB_URL),
                PropertiesUtils.pref().get(SharedConstants.DB_USERNAME),
                PropertiesUtils.pref().get(SharedConstants.DB_PASSWORD)
        );
    }

    public static Database get() {
        if (instance == null) {
            instance = new DaoUtils();
        }
        return db;
    }
}
