package edu.unlam.pacman.ui.modules.tablero;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

public class TableroView extends View<TableroView.MyView> {
    interface MyView extends UiHandler{
        void paint();
    }

    @Override
    protected void onBind() {
        setOpaque(true);
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        uiHandler().paint();
    }

    public void dibujarPared(int x, int y) {
        int size = 50;
        graphics().setColor(Color.RED);
        graphics().fill(new Rectangle2D.Double(x, y, size, size));
        graphics().setColor(Color.BLACK);
        graphics().setStroke(new BasicStroke(2f));
        graphics().drawRect(x, y, size, size);
    }

    public void dibujarPiso(int x, int y) {
        graphics().setColor(Color.YELLOW);
        graphics().drawOval(x, y, 2, 2);
    }
}
