package edu.unlam.pacman.ui.modules.registro.registro;

import java.awt.Graphics2D;

import edu.unlam.pacman.ui.modules.registro.RegistroConstants;
import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

public class RegistroView extends View<RegistroView.MyView> {
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
        return RegistroConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return RegistroConstants.MAX_HEIGHT;
    }
}
