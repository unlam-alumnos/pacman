package edu.unlam.pacman.client.modules.login.login;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.server.service.JugadorService;
import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ServerEvent;
import edu.unlam.pacman.shared.comunication.bus.events.async.ClientEventCallback;
import edu.unlam.pacman.shared.comunication.bus.events.async.ClientEventRequest;
import edu.unlam.pacman.shared.comunication.bus.messages.async.AutenticarJugadorMessageCallback;
import edu.unlam.pacman.shared.comunication.bus.messages.async.AutenticarJugadorMessageRequest;
import edu.unlam.pacman.shared.exception.ServiceException;
import edu.unlam.pacman.shared.model.JugadorActual;
import edu.unlam.pacman.shared.util.PropertiesUtils;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class LoginPresenter extends Presenter<LoginView> implements LoginView.MyView {
    private boolean connected;
    private JugadorService jugadorService;

    public LoginPresenter() {
        super(new LoginView());
        this.jugadorService = JugadorService.getInstance();
        if ("true".equals(PropertiesUtils.pref().get(SharedConstants.GAME_SERVER))) {
            eventBus.post(new ServerEvent(getView().getServerIp(), 8888));
            eventBus.post(new ClientEventRequest(getView().getServerIp(), 8888));
            getView().hideServerIp();
        }
    }

    @Override
    public void login() {
        if (!connected) {
            eventBus.post(new ClientEventRequest(getView().getServerIp(), 8888));
        } else {
            doLogin();
        }
    }

    @Subscribe
    public void handleClientCallbackEvent(ClientEventCallback event) {
        this.connected = true;
        if (!"true".equals(PropertiesUtils.pref().get(SharedConstants.GAME_SERVER))) {
            doLogin();
        }
    }

    private void doLogin() {
        String username = getView().getUsername();
        String password = getView().getPassword();
        communicationHandler.send(new AutenticarJugadorMessageRequest(username, password), AutenticarJugadorMessageRequest.class);
    }

    @Override
    public void register() {
        eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.REGISTRO));
    }

    @Subscribe
    public void handleScreenEvent(ScreenEvent screenEvent) {
        if (ScreenEvent.ScreenType.LOGIN.equals(screenEvent.getScreenType())) {
            getView().clear();
        }
    }

    @Subscribe
    public void handleAutenticarJugadorMessageRequest(AutenticarJugadorMessageRequest request) {
        if ("true".equals(PropertiesUtils.pref().get(SharedConstants.GAME_SERVER))) {
            try {
                Jugador jugador = jugadorService.getByUsernameAndPassword(request.getUsername(), request.getPassword());
                communicationHandler.send(new AutenticarJugadorMessageCallback(request.getSender(), jugador), AutenticarJugadorMessageCallback.class);
            } catch (ServiceException e) {
                getView().error(e.getMessage());
            }
        }
    }

    @Subscribe
    public void handleAutenticarJugadorMessageCallback(AutenticarJugadorMessageCallback callback) {
        if (PropertiesUtils.pref().get(SharedConstants.CLIENT_ID).equals(callback.getSender())) {
            Jugador jugador = callback.getJugador();
            if (jugador != null) {
                JugadorActual.set(jugador);
                getView().clear();
                eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.MENU));
            } else {
                getView().error("Jugador no existente");
            }
        }
    }

    /**
     * Do not use - Only for test purposes
     *
     * @param jugadorService
     */

    public void setJugadorService(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
