package org.project.youtube.Server.Model.Network;

import org.json.JSONObject;
import org.project.youtube.Server.Model.Database.ClientService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket; // the client socket
    private DataInputStream in; // input stream to receive data from the client
    private DataOutputStream out; // output stream to send data to the client

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            String req;
            while (true){
                req = in.readUTF();
                JSONObject reqJson = new JSONObject(req);
                JSONObject data = reqJson.getJSONObject("reqData");

                switch (reqJson.getString("reqType")) {
                    case "login" -> ClientService.login(data);

                }
            }

        }
        catch (IOException e){
            System.err.println("| <IO Exception>");
        }
        finally {

        }
    }
}