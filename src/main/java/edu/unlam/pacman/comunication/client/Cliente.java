package edu.unlam.pacman.comunication.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

    private Socket client;
    private String userName = null;

    public Cliente(String serverIP, int serverPort) {
        try {
            client = new Socket(serverIP, serverPort);
        } catch (IOException e) {
            System.out.println("No se pudo conectar con el servidor, cerrando el  programa...");
            System.exit(1);
        }
    }

    public void send() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            //Se lee desde el host del usuario y dirige el flujo o informaci�n al server
            PrintStream ps = new PrintStream(client.getOutputStream());

            String data;
            while ((data = br.readLine()) != null) {

                if (data.contains(".exit")) {
                    System.out.println("Cerrando Aplicacion...");
                    this.close();
                    System.exit(1);
                } else

                // Si se toca enter sin haber ingresado enter, lo ignoro
                if (!data.equals("")) {
                    ps.println(userName + " dijo :  " + data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return client;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static void main(String args[]) {

        Cliente cliente = new Cliente("localhost", 8888);

        try {
            System.out.println("Ingrese su nombre de usuario: ");
            System.in.skip(System.in.available());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            cliente.setUserName(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n(Ingrese .exit para cerrar la aplicacion)\n");

        new ThreadCliente(cliente.getSocket()).start();

        cliente.send();
        cliente.close();
    }
}
