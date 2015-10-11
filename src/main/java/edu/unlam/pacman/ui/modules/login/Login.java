package edu.unlam.pacman.ui.modules.login;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import edu.unlam.pacman.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.ui.modules.BaseFrame;
import edu.unlam.pacman.ui.modules.login.login.LoginPresenter;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Login extends BaseFrame {
    public Login() throws HeadlessException {
        // Create and set up the window.
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(LoginConstants.MAX_WIDTH, LoginConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected void initContent() {
        addComponent(new LoginPresenter());
    }

    @Override
    protected ScreenEvent.ScreenType getScreenType() {
        return ScreenEvent.ScreenType.LOGIN;
    }
}
