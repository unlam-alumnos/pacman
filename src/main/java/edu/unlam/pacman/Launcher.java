package edu.unlam.pacman;

import edu.unlam.pacman.common.Constants;
import edu.unlam.pacman.ui.modules.fantasma.FantasmaPresenter;
import edu.unlam.pacman.ui.modules.pacman.PacmanPresenter;
import edu.unlam.pacman.ui.modules.tablero.TableroPresenter;
import edu.unlam.pacman.ui.mvp.Presenter;

import javax.swing.*;
import java.awt.*;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Launcher extends JFrame {
    public static Integer index = 1;

    public Launcher() throws HeadlessException {
        // Create and set up the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Launcher().setVisible(true);
            }
        });
    }

    private void initContent() {
        addComponent(new TableroPresenter());
        addComponent(new PacmanPresenter());
        addComponent(new FantasmaPresenter());
    }

    private void addComponent(Presenter presenter) {
        getLayeredPane().add(presenter.getView(), index);
        index++;
    }
}
