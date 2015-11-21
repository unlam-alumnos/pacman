package edu.unlam.pacman.server.service;

import java.net.Socket;

import edu.unlam.pacman.shared.comunication.sockets.client.Cliente;
import edu.unlam.pacman.shared.comunication.sockets.client.ThreadCliente;
import edu.unlam.pacman.shared.comunication.sockets.server.Server;
import edu.unlam.pacman.shared.comunication.sockets.server.ThreadServer;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 17:47
 */
public class CommunicationService {
    private static CommunicationService instance;

    private CommunicationService() {
    }

    public static CommunicationService getInstance() {
        if (instance == null) {
            instance = new CommunicationService();
        }
        return instance;
    }

    public void initServer(int port) {
        Server server;
        Socket socket;
        boolean flag = true;

        server = new Server(port, 5, 3);

        server.printInfo();

        while (flag) {
            socket = server.accept();
            if (socket != null){
                new ThreadServer(socket, server.getConnections()).start();
            }
        }
    }

    public void initClient(String ipServer, int portServer) {
        Cliente cliente = new Cliente(ipServer, portServer);

        new ThreadCliente(cliente.getSocket()).start();
    }

}
