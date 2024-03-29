package edu.unlam.pacman.client.mvp;

import edu.unlam.pacman.client.communication.CommunicationHandler;
import edu.unlam.pacman.shared.comunication.bus.Bus;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:25
 */
public abstract class Presenter<V extends View<? extends UiHandler>> {
    protected Bus eventBus;
    protected CommunicationHandler communicationHandler;
    private V view;

    public Presenter(V view) {
        this.view = view;
        init();
    }

    private void init() {
        this.view.setUiHandler((UiHandler) this);
        this.eventBus = Bus.getInstance();
        this.eventBus.register(this);
        this.communicationHandler = CommunicationHandler.getInstance();
    }

    public V getView() {
        return view;
    }

    /**
     * Only for testing purposes
     * @param view
     */
    public void setView(V view) {
        this.view = view;
        init();
    }

    public Bus getEventBus() {
        return eventBus;
    }

    /**
     * Only for testing purposes
     * @param eventBus
     */
    public void setEventBus(Bus eventBus) {
        this.eventBus = eventBus;
    }
}
