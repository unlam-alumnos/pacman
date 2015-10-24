package edu.unlam.pacman.client.modules.juego.tablero;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;
import edu.unlam.pacman.shared.model.Coordenada;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

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
        // Si saco el bloque este dejan de funcionar bien las frutas comunes
        graphics().setColor(Color.GRAY);
        graphics().fill(new Rectangle2D.Double(x, y, ancho, alto));
        graphics().setColor(Color.BLACK);
        graphics().setStroke(new BasicStroke(2f));
        graphics().drawRect(x, y, ancho, alto);

        ImageIcon sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "tablero/pared.png");
        graphics().drawImage(sprite.getImage(), x, y, ancho, alto, this);
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

    public void dibujarFrutaEspecial(Coordenada coordenada, int ancho, int alto) {
        int x = coordenada.getX();
        int y = coordenada.getY();
        ImageIcon sprite = new ImageIcon(JuegoConstants.SPRITES_PATH + "tablero/fruta_especial.png");
        graphics().drawImage(sprite.getImage(), x, y, ancho, alto, this);
    }

    public void dibujarTimer(Coordenada coordenada, Integer tiempo){
        int x = coordenada.getX();
        int y = coordenada.getY();
        String cronometro = tiempo.toString();

        cronometro = tiempo < 10 ? "0" + cronometro : cronometro;
        graphics().setColor(tiempo < 10 ? Color.RED : Color.WHITE);

        graphics().setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Calibri", Font.BOLD, 25);
        graphics().setFont(font);
        graphics().drawString(cronometro, x, y+19);
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
