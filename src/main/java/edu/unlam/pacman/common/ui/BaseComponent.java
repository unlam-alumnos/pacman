package edu.unlam.pacman.common.ui;

import javax.swing.JPanel;

import edu.unlam.pacman.common.Constants;
import edu.unlam.pacman.events.Bus;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:32
 */
public class BaseComponent extends JPanel {
    public BaseComponent() {
        setBounds(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
        Bus.register(this);
    }
}
