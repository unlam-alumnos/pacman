package edu.unlam.pacman.client.modules.resultado.resultado;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import edu.unlam.pacman.client.modules.juego.JuegoConstants;
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

        /*
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
        */

    }

    @Override
    public void paintComponent(Graphics2D g2) {
        ImageIcon letras = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "/letras.png"));
        ImageIcon numeros = new ImageIcon(getClass().getResource(JuegoConstants.SPRITES_PATH + "/numeros.png"));

        String texto = "fin de la partida";
        int sizeLetra = 25;
        int y = 140;
        int dirImgY = 0;

        int tamImgSprite = 8;
        int asciia = (int)'a';
        int asciiz = (int)'z';
        int ascii0 = (int)'0';
        int ascii9 = (int)'9';

        for (int i = 0; i < texto.length(); i++){
            int letra = (int)texto.charAt(i);
            int x = 10 + i*sizeLetra;
            graphics().drawImage(letras.getImage(), x, y, x+sizeLetra, y+sizeLetra, tamImgSprite*(letra-asciia), dirImgY, tamImgSprite*(letra-(asciia-1)), tamImgSprite , this);
        }

        texto = "";
        y = 180;
        for (int i = 0; i < texto.length(); i++){
            int letra = (int)texto.charAt(i);
            int x = 10 + i*sizeLetra;
            if (letra >= asciia && letra <= asciiz){
                graphics().drawImage(letras.getImage(), x, y, x+sizeLetra, y+sizeLetra, tamImgSprite*(letra-asciia), dirImgY, tamImgSprite*(letra-(asciia-1)), tamImgSprite , this);
            }else if (letra >= ascii0 && letra <= ascii9){
                graphics().drawImage(numeros.getImage(), x, y, x+sizeLetra, y+sizeLetra, tamImgSprite*(letra-ascii0), dirImgY, tamImgSprite*(letra-(ascii0-1)), tamImgSprite , this);
            }
        }
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
