package edu.unlam.pacman.client.mvp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:23
 */
public abstract class View<M extends UiHandler> extends JPanel {
    private M uiHandler;
    private Graphics2D g2;

    public View() {
        onBind();

        setBounds(0, 0, getViewWidth(), getViewHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.g2 = (Graphics2D) g;
        paintComponent(g2);
    }

    public void info(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void error(String message) {
        JOptionPane.showMessageDialog(null, "( ! ) - " + message);
    }

    protected M uiHandler() {
        return uiHandler;
    }

    public Graphics2D graphics() {
        return g2;
    }

    public void setUiHandler(UiHandler uiHandler) {
        this.uiHandler = (M) uiHandler;
    }

    protected abstract void paintComponent(Graphics2D g2);

    protected abstract int getViewWidth();

    protected abstract int getViewHeight();

    protected abstract void onBind();
}
