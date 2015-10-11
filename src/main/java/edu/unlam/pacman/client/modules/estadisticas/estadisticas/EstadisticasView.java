package edu.unlam.pacman.client.modules.estadisticas.estadisticas;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import edu.unlam.pacman.client.modules.estadisticas.EstadisticasConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;

public class EstadisticasView extends View<EstadisticasView.MyView> {
    interface MyView extends UiHandler {
    }

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);

        setBounds(100, 100, 450, 300);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Estadisticas del Jugador:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 11, 216, 20);
        add(lblNewLabel);

        JLabel lbUsuario = new JLabel("[Usuario]");
        lbUsuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        lbUsuario.setBounds(236, 11, 188, 20);
        add(lbUsuario);

        JLabel lbPartidasJugadasText = new JLabel("Partidas Jugadas:");
        lbPartidasJugadasText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbPartidasJugadasText.setBounds(10, 42, 165, 20);
        add(lbPartidasJugadasText);

        JLabel lbPartidasJugadasValor = new JLabel("[PartidasJugadasValor]");
        lbPartidasJugadasValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbPartidasJugadasValor.setBounds(185, 42, 239, 20);
        add(lbPartidasJugadasValor);

        JLabel lbPartidasGanadasText = new JLabel("Partidas Ganadas:");
        lbPartidasGanadasText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbPartidasGanadasText.setBounds(10, 73, 165, 20);
        add(lbPartidasGanadasText);

        JLabel lbPartidasGanadasValor = new JLabel("[PartidasGanadasValor]");
        lbPartidasGanadasValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbPartidasGanadasValor.setBounds(185, 73, 239, 20);
        add(lbPartidasGanadasValor);

        JLabel lbPromedioDeVictoriasText = new JLabel("Promedio de Victorias:");
        lbPromedioDeVictoriasText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbPromedioDeVictoriasText.setBounds(10, 104, 165, 20);
        add(lbPromedioDeVictoriasText);

        JLabel lbPromedioDeVictoriasValor = new JLabel("[PromedioDeVictoriasValor]");
        lbPromedioDeVictoriasValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbPromedioDeVictoriasValor.setBounds(185, 104, 239, 20);
        add(lbPromedioDeVictoriasValor);

        JLabel lbUsoPacmanText = new JLabel("Uso Pacman:");
        lbUsoPacmanText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbUsoPacmanText.setBounds(10, 135, 165, 20);
        add(lbUsoPacmanText);

        JLabel lbUsoPacmanValor = new JLabel("[UsoPacmanValor]");
        lbUsoPacmanValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbUsoPacmanValor.setBounds(185, 135, 239, 20);
        add(lbUsoPacmanValor);

        JLabel lbUsoFantasmaText = new JLabel("Uso Fantasma:");
        lbUsoFantasmaText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbUsoFantasmaText.setBounds(10, 166, 165, 20);
        add(lbUsoFantasmaText);

        JLabel lbUsoFantasmaValor = new JLabel("[UsoFantasmaValor]");
        lbUsoFantasmaValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbUsoFantasmaValor.setBounds(185, 166, 239, 20);
        add(lbUsoFantasmaValor);

        JLabel lbTiempoTotalText = new JLabel("Tiempo Total de Juego:");
        lbTiempoTotalText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbTiempoTotalText.setBounds(10, 197, 165, 20);
        add(lbTiempoTotalText);

        JLabel lbTiempoTotalValor = new JLabel("[TiempoTotalValor]");
        lbTiempoTotalValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbTiempoTotalValor.setBounds(185, 197, 239, 20);
        add(lbTiempoTotalValor);
    }

    @Override
    public void paintComponent(Graphics2D g2) {

    }

    @Override
    protected int getViewWidth() {
        return EstadisticasConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return EstadisticasConstants.MAX_HEIGHT;
    }
}
