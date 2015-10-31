package edu.unlam.pacman.shared.comunication.sockets.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import edu.unlam.pacman.shared.comunication.bus.Bus;

public class ThreadCliente extends Thread{
    private Bus eventBus;
    private Socket socket;

    public ThreadCliente(Socket socket) {
        super("ThreadCliente");
        this.socket = socket;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void run() {
        DataInputStream data;
        String temp = null;

        try {
            do {
                if (temp != null){
                    System.out.println("Mensaje: " + temp);
                }
                data = new DataInputStream(socket.getInputStream());
            } while ((temp = data.readLine()) != null);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
