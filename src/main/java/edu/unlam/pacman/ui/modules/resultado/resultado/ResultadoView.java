package edu.unlam.pacman.ui.modules.resultado.resultado;

import java.awt.Graphics2D;

import edu.unlam.pacman.ui.modules.resultado.ResultadoConstants;
import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

public class ResultadoView extends View<ResultadoView.MyView> {
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
        return ResultadoConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return ResultadoConstants.MAX_HEIGHT;
    }
}
