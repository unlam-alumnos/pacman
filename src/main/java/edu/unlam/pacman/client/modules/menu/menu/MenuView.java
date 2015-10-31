package edu.unlam.pacman.client.modules.menu.menu;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.unlam.pacman.client.modules.menu.MenuConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;

public class MenuView extends View<MenuView.MyView> {
    interface MyView extends UiHandler {
        void crearPartida();
        void unirseAPartida();
    }

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);

        setBounds(100, 100, 450, 300);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JButton btnCrearPartida = new JButton("Crear Partida");
        btnCrearPartida.setBounds(10, 11, 137, 23);
        btnCrearPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiHandler().crearPartida();
            }
        });
        add(btnCrearPartida);

        JButton btnUnirseAPartida = new JButton("Unirse a Partida");
        btnUnirseAPartida.setBounds(10, 114, 137, 23);
        btnUnirseAPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiHandler().unirseAPartida();
            }
        });
        add(btnUnirseAPartida);

        JButton btnVerPartida = new JButton("Ver Partida");
        btnVerPartida.setBounds(10, 45, 137, 23);
        add(btnVerPartida);

        JButton btnEstadisticas = new JButton("Estadisticas");
        btnEstadisticas.setBounds(10, 228, 137, 23);
        add(btnEstadisticas);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(157, 11, 267, 92);
        add(scrollPane);

        JList listJugadores = new JList();
        scrollPane.setViewportView(listJugadores);

        JButton btnEmpezarPartida = new JButton("Empezar Partida");
        btnEmpezarPartida.setBounds(10, 79, 137, 23);
        add(btnEmpezarPartida);

        JTextField txtIpServidor = new JTextField();
        txtIpServidor.setText("000.000.000.000");
        txtIpServidor.setBounds(157, 115, 267, 20);
        add(txtIpServidor);
        txtIpServidor.setColumns(10);
    }

    @Override
    public void paintComponent(Graphics2D g2) {

    }

    @Override
    protected int getViewWidth() {
        return MenuConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return MenuConstants.MAX_HEIGHT;
    }
}
