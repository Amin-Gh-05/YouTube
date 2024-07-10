package org.project.youtube.Server;

import org.project.youtube.Server.Model.Network.ClientHandler;
import org.project.youtube.Server.Model.Network.FileTransfer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    private static final ExecutorService pool = Executors.newFixedThreadPool(10);
    private static ServerSocket serverSocket;
    public static ServerSocket serverFileTransferSocket;

    public static final String SERVER_RESOURCES_PATH = "C:/YouTubeServer";
    public static final String VIDEO_PATH = "C:/YouTubeServer/video";
    public static final String SHORT_PATH = "C:/YouTubeServer/short";


    public static void main(String[] args) {
        try {
            Files.createDirectories(Paths.get(SERVER_RESOURCES_PATH));
            Files.createDirectories(Paths.get(VIDEO_PATH));
            Files.createDirectories(Paths.get(SHORT_PATH));
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
            serverSocket.close();
            serverFileTransferSocket.close();
        }
        pool.shutdown();
    }
}