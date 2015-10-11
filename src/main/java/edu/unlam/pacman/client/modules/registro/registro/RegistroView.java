package edu.unlam.pacman.client.modules.registro.registro;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.unlam.pacman.client.modules.registro.RegistroConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;

public class RegistroView extends View<RegistroView.MyView> {
    interface MyView extends UiHandler {
        void register(String username, String password, String passwordConfirmation);
    }

    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private JPasswordField txtPassConfirm;

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);

        setBounds(100, 100, 450, 300);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(174, 11, 135, 20);
        add(txtUsuario);
        txtUsuario.setColumns(10);

        txtPass = new JPasswordField();
        txtPass.setBounds(174, 42, 135, 20);
        add(txtPass);

        txtPassConfirm = new JPasswordField();
        txtPassConfirm.setBounds(174, 73, 135, 20);
        add(txtPassConfirm);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(172, 104, 137, 23);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiHandler().register(txtUsuario.getText(), txtPass.getText(), txtPassConfirm.getText());
            }
        });
        add(btnAceptar);

        JLabel lbUsuario = new JLabel("Usuario:");
        lbUsuario.setBounds(124, 14, 40, 14);
        add(lbUsuario);

        JLabel lbPass = new JLabel("Contrase\u00F1a:");
        lbPass.setBounds(104, 45, 60, 14);
        add(lbPass);

        JLabel lblConfirmarPass = new JLabel("Confirmar Contrase\u00F1a:");
        lblConfirmarPass.setBounds(54, 76, 110, 14);
        add(lblConfirmarPass);
    }

    @Override
    public void paintComponent(Graphics2D g2) {

    }

    public void clear() {
        txtUsuario.setText("");
        txtPass.setText("");
        txtPassConfirm.setText("");
    }

    @Override
    protected int getViewWidth() {
        return RegistroConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return RegistroConstants.MAX_HEIGHT;
    }
}
