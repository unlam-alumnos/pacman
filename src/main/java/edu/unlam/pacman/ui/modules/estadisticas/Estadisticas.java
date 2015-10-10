package edu.unlam.pacman.ui.modules.estadisticas;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import edu.unlam.pacman.ui.modules.BaseFrame;
import edu.unlam.pacman.ui.modules.estadisticas.estadisticas.EstadisticasPresenter;
import edu.unlam.pacman.ui.modules.login.LoginConstants;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Estadisticas extends BaseFrame {
    public Estadisticas() throws HeadlessException {
        // Create and set up the window.
        setTitle("Estadisticas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(EstadisticasConstants.MAX_WIDTH, LoginConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected void initContent() {
        addComponent(new EstadisticasPresenter());
    }
}
