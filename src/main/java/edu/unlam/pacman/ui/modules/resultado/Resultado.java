package edu.unlam.pacman.ui.modules.resultado;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.comunication.bus.events.modules.ResultadoEvent;
import edu.unlam.pacman.ui.modules.BaseFrame;
import edu.unlam.pacman.ui.modules.login.LoginConstants;
import edu.unlam.pacman.ui.modules.resultado.resultado.ResultadoPresenter;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Resultado extends BaseFrame {
    public Resultado() throws HeadlessException {
        // Create and set up the window.
        setTitle("Resultado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(ResultadoConstants.MAX_WIDTH, LoginConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected void initContent() {
        addComponent(new ResultadoPresenter());
    }

    @Subscribe
    public void handleScreenEvent(ResultadoEvent resultadoEvent) {
        setVisible(true);
    }
}
