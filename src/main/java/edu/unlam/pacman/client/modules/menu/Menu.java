package edu.unlam.pacman.client.modules.menu;

import edu.unlam.pacman.client.modules.BaseFrame;
import edu.unlam.pacman.client.modules.login.LoginConstants;
import edu.unlam.pacman.client.modules.menu.menu.MenuPresenter;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;

import javax.swing.*;
import java.awt.*;

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
        setResizable(false);
    }

    @Override
    protected void initContent() {
        addComponent(new MenuPresenter());
    }

    @Override
    protected ScreenEvent.ScreenType getScreenType() {
        return ScreenEvent.ScreenType.MENU;
    }
}
