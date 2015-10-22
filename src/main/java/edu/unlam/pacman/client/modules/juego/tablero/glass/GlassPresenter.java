package edu.unlam.pacman.client.modules.juego.tablero.glass;

import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.events.KeyEvent;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:25
 */
public class GlassPresenter extends Presenter<GlassView> implements GlassView.MyView {
    public GlassPresenter() {
        super(new GlassView());
    }

    @Override
    public void broadcast(int keyCode) {
        eventBus.post(new KeyEvent(keyCode));
    }
}
