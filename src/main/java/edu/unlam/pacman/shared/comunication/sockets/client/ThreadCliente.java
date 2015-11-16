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
        DataInputStream data = null;
        String message = null;

        try {
            do {
                try {
                    if (message != null) {
                        String token = "\"}{\"}";
                        String[] messages = {message};
                        if (message.contains(token)) {
                            messages = message.split(token);
                            messages[0] += "\"}";
                            messages[1] += "{\"";
                        }
                        for (String msg : messages) {
                            GameMessage gameMessage = new Gson().fromJson(msg, GameMessage.class);
                            Class clazz = Class.forName(gameMessage.getType());
                            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, GameMessage.class, clazz);
                            GameMessage completeMessage = new Gson().fromJson(msg, type);
                            eventBus.post(clazz.cast(completeMessage.getMessage()));
                        }
                    }
                    data = new DataInputStream(socket.getInputStream());
                } catch (Exception e) {
                    System.err.println("- Error en el parseo del mensaje: " + message);
                }
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
