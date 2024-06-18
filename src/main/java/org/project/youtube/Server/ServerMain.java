package org.project.youtube.Server;

import org.project.youtube.Server.Model.Network.ClientHandler;
import org.project.youtube.Server.Model.Network.FileTransfer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    private static final ExecutorService pool = Executors.newFixedThreadPool(10);
    private static ServerSocket serverSocket;
    public static ServerSocket serverFileTransferSocket;
    private static Thread fileTransferThread;


    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(5431);
            serverFileTransferSocket = new ServerSocket(5430);
            runServer();
        } catch (IOException e) {
            System.out.println("| " + e.getMessage());
        }
    }

    private static void runServer() throws IOException {
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            Socket userFileTransferSocket = serverFileTransferSocket.accept();
            System.out.println("| new client connected");

            // create a thread for each client
            Thread thread = new Thread(new ClientHandler(socket, userFileTransferSocket));
            pool.execute(thread);
        }

        stopServer();
    }

    private static void stopServer() throws IOException {
        if (serverSocket != null) {
            fileTransferThread.interrupt();
            serverSocket.close();
            serverFileTransferSocket.close();
        }
        pool.shutdown();
    }
}