package edu.unlam.pacman.client.modules.juego;

import edu.unlam.pacman.client.modules.BaseFrame;
import edu.unlam.pacman.client.modules.juego.personaje.fantasma.FantasmaPresenter;
import edu.unlam.pacman.client.modules.juego.personaje.pacman.PacmanPresenter;
import edu.unlam.pacman.client.modules.juego.tablero.TableroPresenter;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Juego extends BaseFrame {
    public Juego() throws HeadlessException {
        // Create and set up the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(JuegoConstants.MAX_WIDTH, JuegoConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected void initContent() {
        addComponent(new TableroPresenter());
        addComponent(new FantasmaPresenter());
        addComponent(new PacmanPresenter());
    }

    @Override
    protected ScreenEvent.ScreenType getScreenType() {
        return ScreenEvent.ScreenType.JUEGO;
    }
}
