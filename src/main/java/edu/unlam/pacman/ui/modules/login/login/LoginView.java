package edu.unlam.pacman.ui.modules.login.login;

import java.awt.Graphics2D;

import edu.unlam.pacman.ui.modules.login.LoginConstants;
import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

public class LoginView extends View<LoginView.MyView> {
    interface MyView extends UiHandler {
    }

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics2D g2) {

    }

    @Override
    protected int getViewWidth() {
        return LoginConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return LoginConstants.MAX_HEIGHT;
    }
}
