package edu.unlam.pacman.shared.comunication.sockets.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import edu.unlam.pacman.shared.comunication.bus.Bus;
import edu.unlam.pacman.shared.comunication.bus.messages.GameMessage;

public class ThreadCliente extends Thread{
    private Bus eventBus;
    private Socket socket;

    public ThreadCliente(Socket socket) {
        super("ThreadCliente");
        this.socket = socket;
        this.eventBus = Bus.getInstance();
        this.eventBus.register(this);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void run() {
        DataInputStream data;
        String message = null;

        try {
            do {
                if (message != null){
                    System.out.println(">>>> " + message);
                    GameMessage gameMessage = new Gson().fromJson(message, GameMessage.class);
                    eventBus.post(gameMessage.getMessage());
                }
                data = new DataInputStream(socket.getInputStream());
            } while ((message = data.readLine()) != null);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
