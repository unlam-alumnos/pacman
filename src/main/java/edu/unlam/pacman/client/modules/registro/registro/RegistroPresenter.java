package edu.unlam.pacman.client.modules.registro.registro;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.server.service.JugadorService;
import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.comunication.bus.messages.async.RegistrarJugadorMessageCallback;
import edu.unlam.pacman.shared.comunication.bus.messages.async.RegistrarJugadorMessageRequest;
import edu.unlam.pacman.shared.exception.ServiceException;
import edu.unlam.pacman.shared.util.PropertiesUtils;

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
        communicationHandler.send(new RegistrarJugadorMessageRequest(username, password, passwordConfirmation),
                RegistrarJugadorMessageRequest.class);
    }

    @Subscribe
    public void handleRegistrarJugadorMessageRequest(RegistrarJugadorMessageRequest request) {
        if ("true".equals(PropertiesUtils.pref().get(SharedConstants.GAME_SERVER))) {
            try {
                jugadorService.register(request.getUsername(), request.getPassword(), request.getPasswordConfirmation());
                communicationHandler.send(new RegistrarJugadorMessageCallback(request.getSender(), true, "Jugador registrado con exito"),
                        RegistrarJugadorMessageCallback.class);
            } catch (ServiceException e) {
                communicationHandler.send(new RegistrarJugadorMessageCallback(request.getSender(), false, "( ! ) - " + e.getMessage()),
                        RegistrarJugadorMessageCallback.class);
            }
        }
    }

    @Subscribe
    public void handleRegistrarJugadorMessageCallback(RegistrarJugadorMessageCallback callback) {
        if (PropertiesUtils.pref().get(SharedConstants.CLIENT_ID).equals(callback.getSender())) {
            if (callback.isSuccess()) {
                getView().info(callback.getMessage());
                getView().clear();
                eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.LOGIN));
            } else {
                getView().error(callback.getMessage());
            }
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
