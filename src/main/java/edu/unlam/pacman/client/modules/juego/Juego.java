package edu.unlam.pacman.client.modules.juego;

import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.modules.BaseFrame;
import edu.unlam.pacman.client.modules.juego.tablero.cronometro.CronometroPresenter;
import edu.unlam.pacman.client.modules.juego.personaje.fantasma.FantasmaPresenter;
import edu.unlam.pacman.client.modules.juego.personaje.pacman.PacmanPresenter;
import edu.unlam.pacman.client.modules.juego.tablero.puntaje.PuntajePresenter;
import edu.unlam.pacman.client.modules.juego.tablero.TableroPresenter;
import edu.unlam.pacman.client.modules.juego.tablero.glass.GlassPresenter;
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
        setResizable(false);
    }

    @Override
    protected void initContent() {
        addComponent(new TableroPresenter());
        addComponent(new PuntajePresenter());
        addComponent(new PacmanPresenter());
        addComponent(new FantasmaPresenter());
        addComponent(new GlassPresenter());
    }

    @Override
    protected ScreenEvent.ScreenType getScreenType() {
        return ScreenEvent.ScreenType.JUEGO;
    }

    @Subscribe
    @Override
    public void handleScreenEvent(ScreenEvent screenEvent) {
        if (getScreenType().equals(screenEvent.getScreenType())) {
            setVisible(true);
            // TODO : Iniciar cronometro del tablero en este instante
            addComponent(new CronometroPresenter());
        } else {
            setVisible(false);
        }
    }
}
