package edu.unlam.pacman.events;

import com.google.common.eventbus.EventBus;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:34
 */
public class Bus {
    private static EventBus bus;
    private static Bus instance;

    private Bus() {
        bus = new EventBus();
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

    public void post(GameEvent event) {
        bus.post(event);
    }
}
