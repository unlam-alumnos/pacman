package edu.unlam.pacman.client.modules;

import java.awt.Graphics;
import java.awt.Graphics2D;

import org.junit.Before;

import edu.unlam.pacman.shared.comunication.bus.Bus;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.client.mvp.View;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author Cristian Miranda
 * @since 10/6/15 - 00:21
 */
public abstract class BasePresenterTest<P extends Presenter, V extends View> {
    protected P presenter;
    protected V view;

    @Before
    public void setup() {
        this.presenter = getPresenter();
        this.view = (V) spy(presenter.getView());
        when(view.graphics()).thenReturn(mock(Graphics2D.class));
        doNothing().when(view).paintComponent(any(Graphics.class));
        this.presenter.setView(view);
        this.presenter.setEventBus(mock(Bus.class));
    }

    protected abstract P getPresenter();
}
