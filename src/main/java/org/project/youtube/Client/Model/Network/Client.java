package org.project.youtube.Client.Model.Network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static Socket socket;
    public static DataOutputStream out;
    public static Thread responseHandlerThread;

    public static void run() throws IOException {
        responseHandlerThread = new Thread(new ResponseHandler(socket));
        out = new DataOutputStream(socket.getOutputStream());
    }

    public static void sendRequest(String req) throws IOException {
        out.writeUTF(req);
        out.flush();
    }

    private static void close() throws IOException {
        if (socket != null) {
            socket.close();
            responseHandlerThread.interrupt();
        }

    }
}