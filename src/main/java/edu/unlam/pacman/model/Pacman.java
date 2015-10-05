package edu.unlam.pacman.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Pacman extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.YELLOW);
        g2.fillOval(200, 200, 40, 40);
    }

    public void comer(Personaje fantasma) {

    }

    public void comer(Casillero frutaEspecial) {

    }

    public void morir() {

    }
}
