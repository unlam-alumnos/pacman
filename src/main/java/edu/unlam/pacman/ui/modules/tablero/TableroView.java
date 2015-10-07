package edu.unlam.pacman.ui.modules.tablero;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import edu.unlam.pacman.common.Coordenada;
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

    public void dibujarPared(Coordenada coordenada, int ancho, int alto) {
        int x = coordenada.getX();
        int y = coordenada.getY();
        graphics().setColor(Color.RED);
        graphics().fill(new Rectangle2D.Double(x, y, ancho, alto));
        graphics().setColor(Color.BLACK);
        graphics().setStroke(new BasicStroke(2f));
        graphics().drawRect(x, y, ancho, alto);
    }

    public void dibujarPiso(Coordenada coordenada, int radio) {
        int x = coordenada.getX();
        int y = coordenada.getY();
        graphics().setColor(Color.YELLOW);
        graphics().drawOval(x, y, radio, radio);
    }
}
