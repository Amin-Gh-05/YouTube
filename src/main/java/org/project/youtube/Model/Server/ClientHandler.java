package org.project.youtube.Model.Server;

import java.net.Socket;

public class ClientHandler implements Runnable {
    Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}