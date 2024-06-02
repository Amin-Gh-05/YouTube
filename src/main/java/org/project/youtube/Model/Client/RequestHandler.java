package org.project.youtube.Model.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestHandler implements Runnable {
    Socket socket;
    DataOutputStream out;

    RequestHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new DataOutputStream(socket.getOutputStream());
    }


    @Override
    public void run() {

    }
}