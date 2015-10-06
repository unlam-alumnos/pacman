package edu.unlam.pacman.ui.mvp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import edu.unlam.pacman.common.Constants;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:23
 */
public abstract class View<M extends UiHandler> extends JPanel {
    private M uiHandler;
    private Graphics2D g2;

    public View() {
        onBind();

        setBounds(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.g2 = (Graphics2D) g;
        paintComponent(g2);
    }

    protected abstract void paintComponent(Graphics2D g2);

    protected M uiHandler() {
        return uiHandler;
    }

    protected abstract void onBind();

    public Graphics2D graphics() {
        return g2;
    }

    public void setUiHandler(UiHandler uiHandler) {
        this.uiHandler = (M) uiHandler;
    }
}
