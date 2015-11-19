package edu.unlam.pacman.client.modules.login.login;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.unlam.pacman.client.modules.login.LoginConstants;
import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;

public class LoginView extends View<LoginView.MyView> {
    interface MyView extends UiHandler {
        void login(String username, String password);
        void register();
    }

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JTextField txtIp;
    private JButton btnAceptar;
    private JButton btnRegister;
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;

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

        txtIp = new JTextField();
        txtIp.setBounds(147, 73, 137, 20);
        txtIp.setText("127.0.0.1");
        add(txtIp);
        txtIp.setColumns(10);

        btnAceptar = new JButton("Login");
        btnAceptar.setBounds(147, 104, 137, 23);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiHandler().login(txtUser.getText(), txtPass.getText());
            }
        });
        add(btnAceptar);

        btnRegister = new JButton("Registrarse");
        btnRegister.setBounds(147, 135, 137, 23);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiHandler().register();
            }
        });
        add(btnRegister);

        label = new JLabel("Usuario:");
        label.setBounds(97, 14, 40, 14);
        add(label);

        label_1 = new JLabel("Contrase\u00F1a:");
        label_1.setBounds(77, 45, 60, 14);
        add(label_1);

        label_2 = new JLabel("IP Servidor:");
        label_2.setBounds(77, 76, 60, 14);
        add(label_2);
    }

    @Override
    public void paintComponent(Graphics2D g2) {

    }

    public void clear() {
        txtUser.setText("");
        txtPass.setText("");
    }

    public String getServerIp() {
        return txtIp.getText();
    }

    public void hideServerIp() {
        label_2.setVisible(false);
        txtIp.setVisible(false);
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
