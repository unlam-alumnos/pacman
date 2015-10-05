package edu.unlam.pacman.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import edu.unlam.pacman.common.Constants;

public class Tablero extends JPanel {

    private Casillero[][] tablero;
    private List<Personaje> personajes;

    public Tablero() {
        setBounds(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
        setOpaque(true);
    }

    public Tablero(Casillero[][] tablero, List<Personaje> personajes) {
        super();
        this.tablero = tablero;
        this.personajes = personajes;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int[][] board = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        int x = 0;
        int y = 0;
        int size = 50;
        for (int i = 0; i < board.length; i++) {
            int[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                g2.setStroke(new BasicStroke(5f));
                if (row[j] == 1) { // Pared
                    g2.setColor(Color.RED);
                    g2.fill(new Rectangle2D.Double(x, y, size, size));
                    g2.setColor(Color.BLACK);
                    g2.setStroke(new BasicStroke(2f));
                    g2.drawRect(x, y, size, size);
                } else if (row[j] == 0) { // Vacío
                    g2.setColor(Color.YELLOW);
                    g2.drawOval(x + size / 2, y + size / 2, 2, 2);
                }
                x += size;
            }
            x = 0;
            y += size;
        }

        setBackground(Color.BLACK);
    }

    public Coordenada dondeRevivir() {
        int x = 0;
        int y = 0;

        // TODO : caclcular la posicion mas alejada de todos los fantasmas dentro del tablero

        return new Coordenada(x, y);
    }
}
