package edu.unlam.pacman.client.modules.menu.menu;

import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.server.service.CommunicationService;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class MenuPresenter extends Presenter<MenuView> implements MenuView.MyView {
    private CommunicationService communicationService;

    public MenuPresenter() {
        super(new MenuView());
        this.communicationService = CommunicationService.getInstance();
    }

    @Override
    public void crearPartida() {
        communicationService.initServer();
    }

    @Override
    public void unirseAPartida() {
        communicationService.initClient();
    }
}
