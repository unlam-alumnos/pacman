package edu.unlam.pacman.shared.comunication.sockets.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import edu.unlam.pacman.shared.comunication.bus.Bus;
import edu.unlam.pacman.shared.comunication.bus.events.LogEvent;

public class Server {
    private String ipAddress;
    private ServerSocket server;
    private Socket client;
    private int port;
    private Collection<Socket> connections;
    public static int activeClients;
    private int maxClients;
    private int minClients;

    public Server(int port, int maxClients, int minClients) {

        this.ipAddress = "127.0.0.1";
        this.port = port;
        this.maxClients = maxClients;
        this.minClients = minClients;
        this.activeClients = 0;
        this.connections = new ArrayList<Socket>();

        try {
            server = new ServerSocket(this.port);

        } catch (IOException e) {
            System.out.println("No se puede escuchar desde el puerto elegido, cerrando Servidor...");
            System.exit(1);
        }
    }

    public Socket accept() {

        activeClients++;

        try {
            client = server.accept();
            if (activeClients > maxClients) {
                PrintStream ps = new PrintStream(client.getOutputStream());
                ps.println("Servidor Lleno");
                client.close();
                return null;
            }
        } catch (Exception e) {
            Bus.getInstance().post(new LogEvent("Error al aceptar conexiones, Cerrando el Servidor..."));
            System.exit(1);
        }
        Bus.getInstance().post(new LogEvent("La conexion NRO " + activeClients + " fue aceptada correctamente."));
        connections.add(client);
        return client;
    }

    public int getPort() {
        return port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public int getMinClients() {
        return minClients;
    }

    public Collection<Socket> getConnections() {
        return connections;
    }

    public void printInfo(){

        Bus.getInstance().post(new LogEvent("DATOS DEL SERVIDOR:\n-------------------\n"));
        Bus.getInstance().post(new LogEvent("IP:\t" + this.getIpAddress()));
        Bus.getInstance().post(new LogEvent("Puerto:\t" + this.getPort()));
    }
}
