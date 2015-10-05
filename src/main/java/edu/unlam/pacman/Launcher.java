package edu.unlam.pacman;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.unlam.pacman.model.Pacman;
import edu.unlam.pacman.model.Tablero;

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

        frame.getLayeredPane().add(new Pacman(), new Integer(2));
        frame.getLayeredPane().add(new Tablero(), new Integer(1));

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
