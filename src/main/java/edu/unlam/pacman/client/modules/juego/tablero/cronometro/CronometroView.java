package edu.unlam.pacman.client.modules.juego.tablero.cronometro;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;
import edu.unlam.pacman.shared.model.Coordenada;

/**
 * Created by gmartin on 27/10/2015.
 */
public class CronometroView extends View<CronometroView.MyView> {

    interface MyView extends UiHandler {
        void paint();
    }

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(false);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        uiHandler().paint();
    }

    @Override
    protected int getViewWidth() {
        return JuegoConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return JuegoConstants.MAX_HEIGHT;
    }

    public void paint(Coordenada coordenada, String time) {
        int x = coordenada.getX();
        int y = coordenada.getY();

        ImageIcon sprite1 = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "tablero/cronometro/" + time.charAt(0) + ".png"));
        ImageIcon sprite2 = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "tablero/cronometro/" + time.charAt(1) + ".png"));
        graphics().drawImage(sprite1.getImage(), x - 2, y + 3, 50, 50, this);
        graphics().drawImage(sprite2.getImage(), x + 48, y + 3, 50, 50, this);
    }
}
