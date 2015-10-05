package edu.unlam.pacman;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.unlam.pacman.ui.modules.pacman.PacmanPresenter;
import edu.unlam.pacman.ui.modules.tablero.TableroView;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Launcher {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Pacman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PacmanPresenter pacmanPresenter = new PacmanPresenter();

        frame.getLayeredPane().add(pacmanPresenter.getView(), new Integer(2));
        frame.getLayeredPane().add(new TableroView(), new Integer(1));

        // Display the window.
        frame.setSize(455, 660);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
