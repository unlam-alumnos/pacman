package edu.unlam.pacman.comunication.sockets.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

public class Server {

    private String hostName;
    private String ipAddress;
    private ServerSocket server;
    private Socket client;
    private int port;
    private Collection<Socket> connections;
    public static int activeClients;
    private int maxClients;
    private int minClients;

    public Server(int port, int maxClients, int minClients) {

        try {
            this.hostName = InetAddress.getLocalHost().getHostName();
            this.ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

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
            System.out.println("Error al aceptar conexiones, Cerrando el Servidor...");
            System.exit(1);
        }
        System.out.println("La conexion NRO " + activeClients + " fue aceptada correctamente.");
        connections.add(client);
        return client;
    }

    public void stop() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getHostName() {
        return hostName;
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
        System.out.println("DATOS DEL SERVIDOR:\n-------------------\n");
        System.out.println("Hostname:\t" + this.getHostName());
        System.out.println("IP:\t" + this.getIpAddress());
        System.out.println("Puerto:\t" + this.getPort());
    }

    public static void main(String[] args) {
        Server server;
        Socket socket;
        boolean flag = true;

        server = new Server(8888, 5, 3);

        server.printInfo();

        System.out.println("\nServidor escuchando...");

        while (flag) {
            socket = server.accept();
            if (socket != null){
                new ThreadServer(socket, server.getConnections()).start();
            }
        }

        server.stop();
    }
}
