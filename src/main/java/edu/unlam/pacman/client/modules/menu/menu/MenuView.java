package edu.unlam.pacman.client.modules.menu.menu;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        void crearPartida(int port);
        void unirseAPartida(String ipServer, int portServer);
    }

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);

        setBounds(100, 100, 450, 300);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        final JButton btnCrearPartida = new JButton("Crear Partida");
        final JButton btnUnirseAPartida = new JButton("Unirse a Partida");
        final JTextField txtIpServidor = new JTextField("127.0.0.1");
        final JButton btnVerPartida = new JButton("Ver Partida");
        final JButton btnEstadisticas = new JButton("Estadisticas");
        final JButton btnEmpezarPartida = new JButton("Empezar Partida");
        final JList listJugadores = new JList();
        final JScrollPane scrollPane = new JScrollPane();

        btnCrearPartida.setBounds(10, 11, 137, 23);
        btnCrearPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiHandler().crearPartida(8888);
                btnCrearPartida.setEnabled(false);
            }
        });
        add(btnCrearPartida);

        btnUnirseAPartida.setBounds(10, 114, 137, 23);
        btnUnirseAPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiHandler().unirseAPartida(txtIpServidor.getText(),8888);
                btnUnirseAPartida.setEnabled(false);
                txtIpServidor.setEnabled(false);
            }
        });
        add(btnUnirseAPartida);

        txtIpServidor.setBounds(157, 115, 267, 20);
        add(txtIpServidor);
        txtIpServidor.setColumns(10);
        txtIpServidor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                    uiHandler().unirseAPartida(txtIpServidor.getText(),8888);
                    btnUnirseAPartida.setEnabled(false);
                    txtIpServidor.setEnabled(false);
                }
            }
        });

        btnVerPartida.setBounds(10, 45, 137, 23);
        add(btnVerPartida);

        btnEstadisticas.setBounds(10, 228, 137, 23);
        add(btnEstadisticas);

        scrollPane.setBounds(157, 11, 267, 92);
        add(scrollPane);

        scrollPane.setViewportView(listJugadores);

        btnEmpezarPartida.setBounds(10, 79, 137, 23);
        add(btnEmpezarPartida);

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
