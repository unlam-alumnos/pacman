package edu.unlam.pacman.ui;

import edu.unlam.pacman.events.Bus;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:25
 */
public abstract class Presenter<T extends Model, V extends View<T>> {
    private T model;
    private V view;

    public Presenter(T model, V view) {
        this.model = model;
        this.view = view;
        Bus.register(this);
    }

    public T getModel() {
        return model;
    }

    public V getView() {
        return view;
    }
}
