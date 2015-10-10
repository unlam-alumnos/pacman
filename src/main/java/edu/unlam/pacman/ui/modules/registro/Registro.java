package edu.unlam.pacman.ui.modules.registro;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.comunication.bus.events.modules.RegistroEvent;
import edu.unlam.pacman.ui.modules.BaseFrame;
import edu.unlam.pacman.ui.modules.login.LoginConstants;
import edu.unlam.pacman.ui.modules.registro.registro.RegistroPresenter;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Registro extends BaseFrame {
    public Registro() throws HeadlessException {
        // Create and set up the window.
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(RegistroConstants.MAX_WIDTH, LoginConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected void initContent() {
        addComponent(new RegistroPresenter());
    }

    @Subscribe
    public void handleScreenEvent(RegistroEvent registroEvent) {
        setVisible(true);
    }
}
