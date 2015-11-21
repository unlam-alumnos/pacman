package edu.unlam.pacman;

import edu.unlam.pacman.client.modules.estadisticas.Estadisticas;
import edu.unlam.pacman.client.modules.juego.Juego;
import edu.unlam.pacman.client.modules.login.Login;
import edu.unlam.pacman.client.modules.menu.Menu;
import edu.unlam.pacman.client.modules.registro.Registro;
import edu.unlam.pacman.client.modules.resultado.Resultado;
import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.util.PropertiesUtils;

import javax.swing.*;
import java.util.UUID;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Launcher {
    public static void main(String[] args) {
        initProperties(args);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
                new Registro().setVisible(false);
                new Menu().setVisible(false);
                new Estadisticas().setVisible(false);
                new Juego().setVisible(false);
                new Resultado().setVisible(false);
            }
        });
    }

    public static void initProperties(String[] args) {
        PropertiesUtils.pref().put(SharedConstants.DB_URL, "jdbc:mysql://localhost:3306/PACMAN");
        PropertiesUtils.pref().put(SharedConstants.DB_USERNAME, "root");
        PropertiesUtils.pref().put(SharedConstants.DB_PASSWORD, "root");
        PropertiesUtils.pref().put(SharedConstants.GAME_LENGTH, "60");
        PropertiesUtils.pref().put(SharedConstants.GAME_SERVER, "client".equals(args[0]) ? Boolean.FALSE.toString() : Boolean.TRUE.toString());
        PropertiesUtils.pref().put(SharedConstants.CLIENT_ID, UUID.randomUUID().toString());
    }
}
