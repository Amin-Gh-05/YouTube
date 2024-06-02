package org.project.youtube.Model.Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ResponseHandler implements Runnable {
    Socket socket;
    DataInputStream in;

    ResponseHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {

    }
}