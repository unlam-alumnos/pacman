package edu.unlam.pacman.ui;

import javax.swing.JPanel;

import edu.unlam.pacman.common.Constants;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:23
 */
public abstract class View<T extends Model> extends JPanel {
    public View() {
        onBind();

        setBounds(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
    }

    protected abstract void onBind();
}
