package edu.unlam.pacman.client.modules.juego.tablero;

/**
 * Created by gmartin on 24/10/2015.
 */
public class Cronometro {
    private int value;
    private char num1;
    private char num2;

    public Cronometro(int valor) {
        this.value = valor;
    }

    private void convert(){
        if (value >= 10){
            this.num1 = Integer.toString(value).charAt(0);
            this.num2 = Integer.toString(value).charAt(1);
        }else{
            this.num1 = '0';
            this.num2 = Integer.toString(value).charAt(0);
        }

    }

    public void more(){
        this.value++;
        convert();
    }

    public void less(){
        this.value--;
        convert();
    }

    public int getValue() {
        return value;
    }

    public String getValueString() {
        return ""+num1+num2;
    }
}
