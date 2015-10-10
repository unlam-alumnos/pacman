package edu.unlam.pacman.ui.modules.menu;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.comunication.bus.events.modules.MenuEvent;
import edu.unlam.pacman.ui.modules.BaseFrame;
import edu.unlam.pacman.ui.modules.login.LoginConstants;
import edu.unlam.pacman.ui.modules.menu.menu.MenuPresenter;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Menu extends BaseFrame {
    public Menu() throws HeadlessException {
        // Create and set up the window.
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(MenuConstants.MAX_WIDTH, LoginConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected void initContent() {
        addComponent(new MenuPresenter());
    }

    @Subscribe
    public void handleScreenEvent(MenuEvent menuEvent) {
        setVisible(true);
    }
}
