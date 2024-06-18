package org.project.youtube.Client.Model.Network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ResponseHandler implements Runnable {
    Socket socket;
    public static DataInputStream in;

    ResponseHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {

    }
}