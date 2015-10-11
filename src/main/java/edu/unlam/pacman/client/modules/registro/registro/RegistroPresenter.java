package edu.unlam.pacman.client.modules.registro.registro;

import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.server.service.JugadorService;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.exception.ServiceException;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class RegistroPresenter extends Presenter<RegistroView> implements RegistroView.MyView {

    private JugadorService jugadorService;

    public RegistroPresenter() {
        super(new RegistroView());
        this.jugadorService = JugadorService.getInstance();
    }

    @Override
    public void register(String username, String password, String passwordConfirmation) {
        try {
            jugadorService.register(username, password, passwordConfirmation);
            getView().info("Jugador registrado con éxito");
            getView().clear();
            eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.LOGIN));
        } catch (ServiceException e) {
            getView().error("( ! ) - " + e.getMessage());
        }
    }

    /**
     * Do not use - only for testing purposes
     *
     * @param service
     */
    public void setJugadorService(JugadorService service) {
        this.jugadorService = service;
    }
}
