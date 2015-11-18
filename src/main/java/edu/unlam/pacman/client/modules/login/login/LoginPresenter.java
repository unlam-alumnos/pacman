package edu.unlam.pacman.client.modules.login.login;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.server.service.JugadorService;
import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ServerEvent;
import edu.unlam.pacman.shared.exception.ServiceException;
import edu.unlam.pacman.shared.model.JugadorActual;
import edu.unlam.pacman.shared.util.PropertiesUtils;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class LoginPresenter extends Presenter<LoginView> implements LoginView.MyView {
    private JugadorService jugadorService;

    public LoginPresenter() {
        super(new LoginView());
        this.jugadorService = JugadorService.getInstance();
        if ("true".equals(PropertiesUtils.pref().get(SharedConstants.GAME_SERVER, null))) {
            eventBus.post(new ServerEvent(getView().getServerIp(), 8888));
            getView().hideServerIp();
        }
    }

    @Override
    public void login(String username, String password) {
        try {
            Jugador jugador = jugadorService.getByUsernameAndPassword(username, password);
            if (jugador != null) {
                JugadorActual.set(jugador);
                getView().clear();
                eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.MENU));
            } else {
                getView().error("Jugador no existente");
            }
        } catch (ServiceException e) {
            getView().error(e.getMessage());
        }
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

    /**
     * Do not use - Only for test purposes
     *
     * @param jugadorService
     */
    public void setJugadorService(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }
}
