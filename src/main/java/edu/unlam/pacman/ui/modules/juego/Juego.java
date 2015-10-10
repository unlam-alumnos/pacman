package edu.unlam.pacman.ui.modules.juego;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import edu.unlam.pacman.ui.modules.BaseFrame;
import edu.unlam.pacman.ui.modules.juego.pacman.PacmanPresenter;
import edu.unlam.pacman.ui.modules.juego.tablero.TableroPresenter;

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
        addComponent(new PacmanPresenter());
    }
}
