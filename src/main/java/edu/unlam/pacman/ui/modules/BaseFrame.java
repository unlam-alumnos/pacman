package edu.unlam.pacman.ui.modules;

import javax.swing.JFrame;

import edu.unlam.pacman.comunication.bus.Bus;
import edu.unlam.pacman.ui.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/10/15 - 13:15
 */
public abstract class BaseFrame extends JFrame {
    protected static Integer index = 1;

    public BaseFrame() {
        Bus.getInstance().register(this);
    }

    protected void addComponent(Presenter presenter) {
        getLayeredPane().add(presenter.getView(), index);
        index++;
    }

    /**
     * Agrega los distintos presenters a la ventana
     */
    protected abstract void initContent();
}
