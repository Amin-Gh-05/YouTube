package org.project.youtube.Client.Model.Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static Socket fileTransferSocket;
    private static Socket socket;
    private static DataOutputStream out;
    private static DataInputStream in;

    public static void run() throws IOException {
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    public static Socket getFileTransferSocket() {
        return fileTransferSocket;
    }

    public static void setFileTransferSocket(Socket fileTransferSocket) {
        Client.fileTransferSocket = fileTransferSocket;
    }

    public static void setSocket(Socket socket) {
        Client.socket = socket;
    }

    public static void sendRequest(String req) throws IOException {
        out.writeUTF(req);
        out.flush();
    }

    public static String getStringResponse() throws IOException {
        return in.readUTF();
    }

    public static boolean getBooleanResponse() throws IOException {
        return in.readBoolean();
    }

    public static void close() throws IOException {
        if (socket != null) {
            in.close();
            out.close();
            socket.close();
            fileTransferSocket.close();
        }
    }
}