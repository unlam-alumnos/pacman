package edu.unlam.pacman.ui.modules.registro;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import edu.unlam.pacman.ui.modules.BaseFrame;
import edu.unlam.pacman.ui.modules.login.LoginConstants;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Registro extends BaseFrame {
    public Registro() throws HeadlessException {
        // Create and set up the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(RegistroConstants.MAX_WIDTH, LoginConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected void initContent() {

    }
}
