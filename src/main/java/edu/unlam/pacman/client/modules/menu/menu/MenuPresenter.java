package edu.unlam.pacman.client.modules.menu.menu;

import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.events.ClientEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ServerEvent;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class MenuPresenter extends Presenter<MenuView> implements MenuView.MyView {
    public MenuPresenter() {
        super(new MenuView());
    }

    @Override
    public void crearPartida() {
        eventBus.post(new ServerEvent());
    }

    @Override
    public void unirseAPartida() {
        eventBus.post(new ClientEvent());
    }
}
