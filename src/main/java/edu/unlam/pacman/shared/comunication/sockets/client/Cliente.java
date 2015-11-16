package edu.unlam.pacman.shared.comunication.sockets.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;

import edu.unlam.pacman.shared.comunication.bus.Bus;
import edu.unlam.pacman.shared.comunication.bus.messages.GameMessage;

public class Cliente {
    private Bus eventBus;
    private Socket client;

    public Cliente(String serverIP, int serverPort) {
        try {
            client = new Socket(serverIP, serverPort);
        } catch (IOException e) {
            System.out.println("No se pudo conectar con el servidor, cerrando el  programa...");
            System.exit(1);
        }
        this.eventBus = Bus.getInstance();
        this.eventBus.register(this);
    }

    @Subscribe
    public void handleCommunicationEvent(GameMessage event) {
        try {
            PrintStream ps = new PrintStream(client.getOutputStream());
            ps.println(new Gson().toJson(event));
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
}
