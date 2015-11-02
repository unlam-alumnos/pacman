package edu.unlam.pacman.client.modules.juego.cronometro;

/**
 * Created by gmartin on 27/10/2015.
 */
public class Cronometro {
    private int duracion;
    private char num1;
    private char num2;

    public Cronometro(int duracion) {
        this.duracion = duracion;
    }

    private void convert(){
        if (duracion >= 10){
            this.num1 = Integer.toString(duracion).charAt(0);
            this.num2 = Integer.toString(duracion).charAt(1);
        }else{
            this.num1 = '0';
            this.num2 = Integer.toString(duracion).charAt(0);
        }

    }

    public void less(){
        this.duracion--;
        convert();
    }

    public int getDuracion() {
        return duracion;
    }

    public String getValueString() {
        return ""+num1+num2;
    }
}
