package edu.unlam.pacman.client.modules.juego.personaje.pacman;

import javax.swing.ImageIcon;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.modules.juego.personaje.PersonajeView;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

public class PacmanView extends PersonajeView<PacmanView.MyView> {
    interface MyView extends PersonajeView.MyView {
    }

    @Override
    public void paintPersonaje(Personaje personaje) {
        int x = personaje.getX();
        int y = personaje.getY();
        int width = personaje.getWidth();
        int height = personaje.getHeight();
        int imageIndex = ((Pacman) personaje).getImageIndex();
        Direction direction = personaje.getDirection();
        Status status = personaje.getStatus();

        ImageIcon sprite;

        if(Status.VICTIM.equals(status)) {
            sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "pacman/victim.gif"));
        }else{
            switch (direction){
                case RIGHT:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "pacman/" + JuegoConstants.PACMAN_ANIMATION[imageIndex-1] + "/right.gif"));
                    break;
                case LEFT:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "pacman/" + JuegoConstants.PACMAN_ANIMATION[imageIndex-1] +"/left.gif"));
                    break;
                case UP:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "pacman/" + JuegoConstants.PACMAN_ANIMATION[imageIndex-1] + "/up.gif"));
                    break;
                case DOWN:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "pacman/" + JuegoConstants.PACMAN_ANIMATION[imageIndex-1] + "/down.gif"));
                    break;
                default:
                    sprite = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "pacman/uno/right.gif"));
                    break;
            }
        }
        graphics().drawImage(sprite.getImage(), x, y, width, height, this);
    }
}
