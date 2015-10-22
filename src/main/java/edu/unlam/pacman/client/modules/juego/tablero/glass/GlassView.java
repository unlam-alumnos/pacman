package edu.unlam.pacman.client.modules.juego.tablero.glass;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.unlam.pacman.client.mvp.UiHandler;
import edu.unlam.pacman.client.mvp.View;

public class GlassView extends View<GlassView.MyView> implements KeyListener {
    interface MyView extends UiHandler{
        void broadcast(int keyCode);
    }

    @Override
    protected void onBind() {
        setOpaque(false);
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics2D g2) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        uiHandler().broadcast(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    protected int getViewWidth() {
        return 0;
    }

    @Override
    protected int getViewHeight() {
        return 0;
    }
}
