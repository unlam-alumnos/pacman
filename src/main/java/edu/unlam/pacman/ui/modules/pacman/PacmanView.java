package edu.unlam.pacman.ui.modules.pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

public class PacmanView extends View<PacmanView.MyView> {
    interface MyView extends UiHandler {
        void move(int x, int y);
    }

    /*
     * UI Data
     */
    private int x;
    private int y;
    private int height;
    private int width;

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);
        bindKeys();
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.fillArc(x, y, width, height, 30, 300);
    }

    public void updateCoordenates(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    private void bindKeys() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    uiHandler().move(0, -5);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    uiHandler().move(5, 0);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    uiHandler().move(-5, 0);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    uiHandler().move(0, 5);
                }
                return false;
            }
        });
    }
}
