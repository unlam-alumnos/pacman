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

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Launcher {
    public static void main(String[] args) {
        initProperties();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(false);
                new Registro().setVisible(false);
                new Menu().setVisible(true);
                new Estadisticas().setVisible(false);
                new Juego().setVisible(false);
                new Resultado().setVisible(false);
            }
        });
    }

    public static void initProperties() {
        PropertiesUtils.pref().put(SharedConstants.DB_URL, "jdbc:mysql://crismiranda.net:3306/PACMAN");
        PropertiesUtils.pref().put(SharedConstants.DB_USERNAME, "root");
        PropertiesUtils.pref().put(SharedConstants.DB_PASSWORD, "");
    }
}
