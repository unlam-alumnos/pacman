package edu.unlam.pacman.client.modules.menu.menu;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.events.ClientEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ServerEvent;
import edu.unlam.pacman.shared.comunication.bus.events.StartEvent;
import edu.unlam.pacman.shared.comunication.bus.messages.StartMessage;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class MenuPresenter extends Presenter<MenuView> implements MenuView.MyView {
    public MenuPresenter() {
        super(new MenuView());
    }

    @Override
    public void crearPartida(int port) {
        eventBus.post(new ServerEvent(port));
    }

    @Override
    public void unirseAPartida(String ipServer, int portServer) {
        eventBus.post(new ClientEvent(ipServer,portServer));
    }

    @Override
    public void empezarPartida(){
        communicationHandler.send(new StartMessage(), StartMessage.class);
        eventBus.post(new StartEvent());
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleStartEventCallback(StartEvent e){
        eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.JUEGO));
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleDirectionMessage(StartMessage startMessage) {
        eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.JUEGO));
    }
}
