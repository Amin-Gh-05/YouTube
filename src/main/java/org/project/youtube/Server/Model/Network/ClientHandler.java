package org.project.youtube.Server.Model.Network;

import java.net.Socket;

public class ClientHandler implements Runnable {
    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}