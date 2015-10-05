package edu.unlam.pacman.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.common.ui.BaseComponent;
import edu.unlam.pacman.events.Bus;
import edu.unlam.pacman.events.MoveEvent;

public class Pacman extends BaseComponent {

    private int x = 225;
    private int y = 225;

    private int height = 40;
    private int width = 40;

    public Pacman() {
        super();
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

    @Subscribe
    public void handleMovement(MoveEvent event) {
        x += event.getX();
        y += event.getY();
        repaint();
    }

    public void comer(Personaje fantasma) {

    }

    public void comer(Casillero frutaEspecial) {

    }

    public void morir() {

    }
}
