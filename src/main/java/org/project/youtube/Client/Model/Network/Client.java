package org.project.youtube.Client.Model.Network;

import java.io.IOException;
import java.net.Socket;

public class Client {
    Socket socket;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        run();
    }

    public void run() throws IOException {
        Thread responseHandlerThread = new Thread(new ResponseHandler(socket));
        Thread requestHandlerThread = new Thread(new RequestHandler(socket));
    }

    private void close() throws IOException {
        if (socket != null) {
            socket.close();
        }

    }
}