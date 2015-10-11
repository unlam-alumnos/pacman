package edu.unlam.pacman.shared.comunication.bus;

import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:34
 */
public class Bus {
    private static AsyncEventBus bus;
    private static Bus instance;

    private Bus() {
        bus = new AsyncEventBus(Executors.newCachedThreadPool());
    }

    public static Bus getInstance() {
        if (instance == null) {
            instance = new Bus();
        }
        return instance;
    }

    public void register(Object bean) {
        bus.register(bean);
    }

    public void post(Object event) {
        bus.post(event);
    }
}
