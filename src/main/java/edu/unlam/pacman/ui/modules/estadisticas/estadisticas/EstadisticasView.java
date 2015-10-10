package edu.unlam.pacman.ui.modules.estadisticas.estadisticas;

import java.awt.Graphics2D;

import edu.unlam.pacman.ui.modules.estadisticas.EstadisticasConstants;
import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

public class EstadisticasView extends View<EstadisticasView.MyView> {
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
        return EstadisticasConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return EstadisticasConstants.MAX_HEIGHT;
    }
}
