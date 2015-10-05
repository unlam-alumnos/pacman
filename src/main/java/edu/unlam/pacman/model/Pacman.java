package edu.unlam.pacman.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import edu.unlam.pacman.common.Constants;

public class Pacman extends JPanel {

    private int x = 225;
    private int y = 225;

    private int height = 40;
    private int width = 40;

    public Pacman() {
        setBounds(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
        setOpaque(false);
        setFocusable(true);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            move(0, -5);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            move(5, 0);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            move(-5, 0);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            move(0, 5);
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

    public void move(int dX, int dY) {
        x += dX;
        y += dY;
        repaint();
    }

    public void comer(Personaje fantasma) {

    }

    public void comer(Casillero frutaEspecial) {

    }

    public void morir() {

    }
}
