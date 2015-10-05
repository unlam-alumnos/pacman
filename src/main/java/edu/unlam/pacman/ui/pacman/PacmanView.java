package edu.unlam.pacman.ui.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import edu.unlam.pacman.events.Bus;
import edu.unlam.pacman.events.MoveEvent;
import edu.unlam.pacman.ui.View;

public class PacmanView extends View<Pacman> {
    private int x;
    private int y;
    private int height;
    private int width;

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            Bus.post(new MoveEvent(0, -5));
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            Bus.post(new MoveEvent(5, 0));
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            Bus.post(new MoveEvent(-5, 0));
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            Bus.post(new MoveEvent(0, 5));
                        }
                        return false;
                    }
                });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.YELLOW);
        g2.fillArc(x, y, width, height, 30, 300);
    }

    public void updateCoordenates(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
}
