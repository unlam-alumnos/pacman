package edu.unlam.pacman.ui.modules.menu.menu;

import java.awt.Graphics2D;

import edu.unlam.pacman.ui.modules.menu.MenuConstants;
import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

public class MenuView extends View<MenuView.MyView> {
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
        return MenuConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return MenuConstants.MAX_HEIGHT;
    }
}
