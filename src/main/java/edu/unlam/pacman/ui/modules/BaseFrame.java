package edu.unlam.pacman.ui.modules;

import javax.swing.JFrame;

import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.comunication.bus.Bus;
import edu.unlam.pacman.comunication.bus.events.ScreenEvent;
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

    @Subscribe
    public void handleScreenEvent(ScreenEvent screenEvent) {
        if (getScreenType().equals(screenEvent.getScreenType())) {
            setVisible(true);
        } else {
            setVisible(false);
        }
    }

    /**
     * Agrega los distintos presenters a la ventana
     */
    protected abstract void initContent();

    protected abstract ScreenEvent.ScreenType getScreenType();
}
