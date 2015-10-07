package edu.unlam.pacman.ui.mvp;

import edu.unlam.pacman.events.Bus;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:25
 */
public abstract class Presenter<V extends View<? extends UiHandler>> {
    protected Bus eventBus;
    private V view;

    public Presenter(V view) {
        this.view = view;
        init();
    }

    private void init() {
        this.view.setUiHandler((UiHandler) this);
        this.eventBus = Bus.getInstance();
        this.eventBus.register(this);
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
}
