package edu.unlam.pacman.ui.modules.menu;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import edu.unlam.pacman.ui.modules.BaseFrame;
import edu.unlam.pacman.ui.modules.login.LoginConstants;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Menu extends BaseFrame {
    public Menu() throws HeadlessException {
        // Create and set up the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(MenuConstants.MAX_WIDTH, LoginConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected void initContent() {

    }
}
