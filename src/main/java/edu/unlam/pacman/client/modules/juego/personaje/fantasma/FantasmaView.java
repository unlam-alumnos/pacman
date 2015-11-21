package edu.unlam.pacman.client.modules.juego.personaje.fantasma;

import javax.swing.ImageIcon;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.modules.juego.personaje.PersonajeView;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

public class FantasmaView extends PersonajeView<FantasmaView.MyView> {
    interface MyView extends PersonajeView.MyView {

    }

    @Override
    public void paintPersonaje(Personaje personaje) {
        int x = personaje.getX();
        int y = personaje.getY();
        int width = personaje.getWidth();
        int height = personaje.getHeight();
        Direction direction = personaje.getDirection();
        Status status = personaje.getStatus();

        ImageIcon sprite;

        if(Status.VICTIM.equals(status)) {
            sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "fantasma/" + ((Fantasma) personaje).getColor() + "/victim.png"));
        }else{
            switch (direction){
                case RIGHT:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "fantasma/" + ((Fantasma) personaje).getColor() + "/right.png"));
                    break;
                case LEFT:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "fantasma/" + ((Fantasma) personaje).getColor() +"/left.png"));
                    break;
                case UP:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "fantasma/" + ((Fantasma) personaje).getColor() + "/up.png"));
                    break;
                case DOWN:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "fantasma/" + ((Fantasma) personaje).getColor() + "/down.png"));
                    break;
                default:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "fantasma/" + ((Fantasma) personaje).getColor() + "/right.png"));
                    break;
            }
        }
        graphics().drawImage(sprite.getImage(), x, y, width, height, this);
    }
}
