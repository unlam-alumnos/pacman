package edu.unlam.pacman.ui.modules.fantasma;

import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FantasmaView extends View<FantasmaView.MyView> {
    interface MyView extends UiHandler {
        void move(int x, int y);
        void paintPacman();
    }

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);
        bindKeys();
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        uiHandler().paintPacman();
    }

    public void paintPacman(int x, int y, int width, int height) {
        graphics().setColor(Color.BLUE);
        graphics().fillArc(x, y, width, height, 0, 300);
    }

    private void bindKeys() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                    uiHandler().move(0, -5);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                    uiHandler().move(5, 0);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                    uiHandler().move(-5, 0);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                    uiHandler().move(0, 5);
                }
                return false;
            }
        });
    }
}
