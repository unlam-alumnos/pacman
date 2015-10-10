package edu.unlam.pacman;

import javax.swing.SwingUtilities;

import edu.unlam.pacman.ui.modules.estadisticas.Estadisticas;
import edu.unlam.pacman.ui.modules.juego.Juego;
import edu.unlam.pacman.ui.modules.login.Login;
import edu.unlam.pacman.ui.modules.menu.Menu;
import edu.unlam.pacman.ui.modules.registro.Registro;
import edu.unlam.pacman.ui.modules.resultado.Resultado;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Launcher {
    public static void main(String[] args) {
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
}
