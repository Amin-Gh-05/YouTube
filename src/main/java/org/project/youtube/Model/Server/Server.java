package org.project.youtube.Model.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    ExecutorService pool = Executors.newFixedThreadPool(10);
    ServerSocket serverSocket;

    Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Server server = new Server(serverSocket);
            server.runServer();
        } catch (IOException e) {
            System.out.println("| " + e.getMessage());
        }
    }

    private void runServer() throws IOException {
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            System.out.println("| new client connected");

            // create a thread for each client
            Thread thread = new Thread(new ClientHandler(socket));
            pool.execute(thread);
        }

        stopServer();
    }

    private void stopServer() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
        pool.shutdown();
    }
}