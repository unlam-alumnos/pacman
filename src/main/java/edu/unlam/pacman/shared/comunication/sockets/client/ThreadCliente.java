package edu.unlam.pacman.shared.comunication.sockets.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
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
                if (message != null) {
                    System.out.println(">>>> " + message);
                    GameMessage gameMessage = new Gson().fromJson(message, GameMessage.class);
                    Class clazz = Class.forName(gameMessage.getType());
                    Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, GameMessage.class, clazz);
                    GameMessage completeMessage = new Gson().fromJson(message, type);
                    eventBus.post(clazz.cast(completeMessage.getMessage()));
                }
                data = new DataInputStream(socket.getInputStream());
            } while ((message = data.readLine()) != null);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
