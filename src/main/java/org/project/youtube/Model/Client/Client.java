package org.project.youtube.Model.Client;

import org.project.youtube.Model.Server.ClientHandler;

import java.io.IOException;
import java.net.Socket;

public class Client {
    Socket socket;

    Client(Socket socket) {
        this.socket = socket;
        run();
    }

    public void run() {

    }

    private void close() throws IOException {
        if (socket != null) {
            socket.close();
        }

    }
}