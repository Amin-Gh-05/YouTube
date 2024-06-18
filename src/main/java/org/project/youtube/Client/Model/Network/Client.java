package org.project.youtube.Client.Model.Network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static Socket fileTransferSocket;
    private static Socket socket;
    private static DataOutputStream out;
    private static Thread responseHandlerThread;

    public static void run() throws IOException {
        responseHandlerThread = new Thread(new ResponseHandler(socket));
        out = new DataOutputStream(socket.getOutputStream());
    }

    public static Socket getFileTransferSocket() {
        return fileTransferSocket;
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