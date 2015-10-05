package edu.unlam.pacman.events;

import com.google.common.eventbus.EventBus;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 14:34
 */
public class Bus {
    private static EventBus bus;

    private Bus() {}

    private static void init() {
        if (bus == null) {
            bus = new EventBus();
        }
    }

    public static void register(Object bean) {
        init();
        bus.register(bean);
    }

    public static void post(GameEvent event) {
        init();
        bus.post(event);
    }
}
