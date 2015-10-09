package edu.unlam.pacman.client.modules.juego.tablero;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;
import edu.unlam.pacman.shared.common.Coordenada;

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

    public void dibujarPiso(Coordenada coordenada) {
        dibujarFruta(coordenada,0);
    }
    public void dibujarFruta(Coordenada coordenada, int radio) {
        int x = coordenada.getX();
        int y = coordenada.getY();
        graphics().setColor(Color.YELLOW);
        graphics().drawOval(x, y, radio, radio);
    }

    @Override
    protected int getViewWidth() {
        return JuegoConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return JuegoConstants.MAX_HEIGHT;
    }
}
