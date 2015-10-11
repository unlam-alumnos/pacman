package edu.unlam.pacman.client.modules.login.login;

import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.client.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class LoginPresenter extends Presenter<LoginView> implements LoginView.MyView {
    private Jugador jugador;

    public LoginPresenter() {
        super(new LoginView());
        this.jugador = new Jugador();
    }

    @Override
    public void aceptar() {
        eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.MENU));
    }
}
