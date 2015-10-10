package edu.unlam.pacman.ui.modules.login.login;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.unlam.pacman.ui.modules.login.LoginConstants;
import edu.unlam.pacman.ui.mvp.UiHandler;
import edu.unlam.pacman.ui.mvp.View;

public class LoginView extends View<LoginView.MyView> {
    interface MyView extends UiHandler {
        void aceptar();
    }

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnRegister;
    private JLabel label;
    private JLabel label_1;

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);

        setBounds(100, 100, 450, 300);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        txtUser = new JTextField();
        txtUser.setBounds(147, 11, 137, 20);
        add(txtUser);
        txtUser.setColumns(10);

        txtPass = new JPasswordField();
        txtPass.setBounds(147, 42, 137, 20);
        add(txtPass);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(147, 73, 137, 23);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiHandler().aceptar();
            }
        });
        add(btnAceptar);

        btnRegister = new JButton("Registrarse");
        btnRegister.setBounds(147, 107, 137, 23);
        add(btnRegister);

        label = new JLabel("Usuario:");
        label.setBounds(97, 14, 40, 14);
        add(label);

        label_1 = new JLabel("Contrase\u00F1a:");
        label_1.setBounds(77, 45, 60, 14);
        add(label_1);
    }

    @Override
    public void paintComponent(Graphics2D g2) {

    }

    @Override
    protected int getViewWidth() {
        return LoginConstants.MAX_WIDTH;
    }

    @Override
    protected int getViewHeight() {
        return LoginConstants.MAX_HEIGHT;
    }
}
