package edu.unlam.pacman.client.modules.juego.tablero.puntaje;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.modules.juego.personaje.fantasma.Fantasma;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;

/**
 * Created by gmartin on 27/10/2015.
 */
public class PuntajeView extends View<PuntajeView.MyView> {
    interface MyView extends UiHandler {
        void paintPuntaje();
    }

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        uiHandler().paintPuntaje();
    }

    @Override
    protected int getViewWidth() {
        return JuegoConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return JuegoConstants.MAX_HEIGHT;
    }

    public void paintPuntaje(Personaje personaje, int i){
        String puntaje;
        int x = 100 * i;
        int y = 450;

        ImageIcon spritePersonaje = null;
        switch (personaje.getTipoPersonaje()){
            case "Pacman":
                spritePersonaje = new ImageIcon(JuegoConstants.SPRITES_PATH + "pacman/uno/right.gif");
                break;
            case "Fantasma":
                spritePersonaje = new ImageIcon(JuegoConstants.SPRITES_PATH + "fantasma/" + ((Fantasma) personaje).getColor() + "/right.png");
                break;
        }

        if (personaje.getKills() < 10){
            puntaje = "0" + Integer.toString(personaje.getKills());
        }else{
            puntaje =  Integer.toString(personaje.getKills());
        }
        ImageIcon sprite1 = new ImageIcon(JuegoConstants.SPRITES_PATH + "tablero/cronometro/" + puntaje.charAt(0) + ".png");
        ImageIcon sprite2 = new ImageIcon(JuegoConstants.SPRITES_PATH + "tablero/cronometro/" + puntaje.charAt(1) + ".png");


        graphics().drawImage(spritePersonaje.getImage(), x, y, personaje.getWidth(), personaje.getHeight(), this);
        graphics().drawImage(sprite1.getImage(), x+25, y+1, personaje.getWidth(), personaje.getHeight(), this);
        graphics().drawImage(sprite2.getImage(), x+50, y+1, personaje.getWidth(), personaje.getHeight(), this);

    }
}
