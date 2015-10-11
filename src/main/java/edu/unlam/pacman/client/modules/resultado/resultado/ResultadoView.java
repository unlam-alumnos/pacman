package edu.unlam.pacman.client.modules.resultado.resultado;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import edu.unlam.pacman.client.modules.resultado.ResultadoConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;

public class ResultadoView extends View<ResultadoView.MyView> {
    interface MyView extends UiHandler {
    }

    @Override
    protected void onBind() {
        setOpaque(true);
        setFocusable(true);

        setBounds(100, 100, 450, 300);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lbGanador = new JLabel("GANADOR:");
        lbGanador.setForeground(Color.WHITE);
        lbGanador.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbGanador.setBounds(58, 118, 150, 25);
        add(lbGanador);

        JLabel lbUsuarioValor = new JLabel("[Usuario]");
        lbUsuarioValor.setForeground(Color.WHITE);
        lbUsuarioValor.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbUsuarioValor.setBounds(218, 118, 150, 25);
        add(lbUsuarioValor);

        JLabel lbTiempo = new JLabel("TIEMPO:");
        lbTiempo.setForeground(Color.WHITE);
        lbTiempo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbTiempo.setBounds(58, 147, 150, 25);
        add(lbTiempo);

        JLabel lbTiempoValor = new JLabel("[Tiempo]");
        lbTiempoValor.setForeground(Color.WHITE);
        lbTiempoValor.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbTiempoValor.setBounds(218, 147, 150, 25);
        add(lbTiempoValor);
    }

    @Override
    public void paintComponent(Graphics2D g2) {

    }

    @Override
    protected int getViewWidth() {
        return ResultadoConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return ResultadoConstants.MAX_HEIGHT;
    }
}
