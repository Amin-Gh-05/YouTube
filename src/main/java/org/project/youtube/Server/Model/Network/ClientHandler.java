package org.project.youtube.Server.Model.Network;

import org.json.JSONObject;
import org.project.youtube.Server.Model.Database.ClientService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket; // the client socket
    private static Socket userFileTransferSocket; // file transfer socket for user
    private DataInputStream in; // input stream to receive data from the client
    private DataOutputStream out; // output stream to send data to the client

    public ClientHandler(Socket socket, Socket userFileTransferSocket) throws IOException {
        this.socket = socket;
        ClientHandler.userFileTransferSocket = userFileTransferSocket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    public static Socket getUserFileTransferSocket() {
        return userFileTransferSocket;
    }

    public void sendResponse(String resp) throws IOException {
        out.writeUTF(resp);
        out.flush();
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
                    case "signup" -> sendResponse(ClientService.signup(data));
                    case "findUsername" -> sendResponse(ClientService.findUsername(data));
                    case "findEmail" -> sendResponse(ClientService.findEmail(data));
                    case "login" -> sendResponse(ClientService.login(data));
//                    case "logout" -> ;
//                    case "getRandomTags" -> ;
//                    case "getRandomVideos" -> ;
//                    case "like" -> ;
//                    case "getVideo" -> ;
//                    case "getVideos" -> ;
//                    case "getShort" -> ;
//                    case "getShorts" -> ;
//                    case "getPL" -> ;
//                    case "getPLs" -> ;
//                    case "getChannel" -> ;
//                    case "getChannels" -> ;

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