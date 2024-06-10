package org.project.youtube.Server;

import org.project.youtube.Server.Model.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    ExecutorService pool = Executors.newFixedThreadPool(10);
    ServerSocket serverSocket;

    ServerMain(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5432);
            ServerMain server = new ServerMain(serverSocket);
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