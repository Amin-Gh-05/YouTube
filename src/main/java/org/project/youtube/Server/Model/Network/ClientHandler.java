package org.project.youtube.Server.Model.Network;

import org.json.JSONObject;
import org.project.youtube.Server.Model.Database.ClientService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

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

    public void sendStringResponse(String resp) throws IOException {
        out.writeUTF(resp);
        out.flush();
    }
    public void sendBooleanResponse(boolean resp) throws IOException {
        out.writeBoolean(resp);
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
                    case "findUsername" -> sendBooleanResponse(ClientService.findUsername(data));
                    case "findEmail" -> sendBooleanResponse(ClientService.findEmail(data));
                    case "signup" -> sendStringResponse(ClientService.signup(data));
                    case "login" -> sendStringResponse(ClientService.login(data));
//                    case "logout" -> ;

                    case "getVideo" -> sendStringResponse(ClientService.getVideo(data));
                    case "getChannelVideos" -> sendStringResponse(ClientService.getChannelVideos(data));
                    case "getPlayListVideos" -> sendStringResponse(ClientService.getPlayListVideos(data));
                    case "getShort" -> sendStringResponse(ClientService.getShort(data));
                    case "getChannelShorts" -> sendStringResponse(ClientService.getChannelShorts(data));
                    case "getPlayListShorts" -> sendStringResponse(ClientService.getPlayListShorts(data));
                    case "getPL" -> sendStringResponse(ClientService.getPL(data));
                    case "getPLs" -> sendStringResponse(ClientService.getPls(data));
                    case "getChannel" -> sendStringResponse(ClientService.getChannel(data));
                    case "getChannels" -> sendStringResponse(ClientService.getChannels(data));
//                    case "getRandomTags" -> ;
//                    case "getRandomVideos" -> ;

                    case "updateUser" -> ClientService.updateUser(data);
                    case "updateChannel" -> ClientService.updateChannel(data);
                    case "updateVideo" -> ClientService.updateVideo(data);
                    case "updateShort" -> ClientService.updateShort(data);
                    case "updatePL" -> ClientService.updatePL(data);
                    case "updateVideoComment" -> ClientService.updateVideoComment(data);
                    case "updateShortComment" -> ClientService.updateShortComment(data);
                    case "updateVideoViews" -> ClientService.updateVideoViews(data);
                    case "updateShortViews" -> ClientService.updateShortViews(data);
                    case "subscribe" -> sendBooleanResponse(ClientService.subscribe(data));
                    case "likeVideo" -> sendBooleanResponse(ClientService.likeVideo(data));
                    case "likeShort" -> sendBooleanResponse(ClientService.likeShort(data));
                    case "likeVideoComment" -> sendBooleanResponse(ClientService.likeVideoComment(data));
                    case "likeShortComment" -> sendBooleanResponse(ClientService.likeShortComment(data));

                    case "createChannel" -> ClientService.createChannel(data);
                    case "createVideo" -> ClientService.createVideo(data);
                    case "createShort" -> ClientService.createShort(data);
                    case "createPlaylist" -> ClientService.createPlaylist(data);
                    case "createVideoComment" -> ClientService.createVideoComment(data);
                    case "createShortComment" -> ClientService.createShortComment(data);
                    case "addVideoToPlaylist" -> ClientService.addVideoToPlaylist(data);
                    case "addShortToPlaylist" -> ClientService.addShortToPlaylist(data);

                    case "deleteUser" -> ClientService.deleteUser(data);
                    case "deleteChannel" -> ClientService.deleteChannel(data);
                    case "deleteVideo" -> ClientService.deleteVideo(data);
                    case "deleteShort" -> ClientService.deleteShort(data);
                    case "deletePlaylist" -> ClientService.deletePlaylist(data);
                    case "deleteVideoComment" -> ClientService.deleteVideoComment(data);
                    case "deleteShortComment" -> ClientService.deleteShortComment(data);
                    case "unSubscribeChannel" -> sendBooleanResponse(ClientService.unSubscribeChannel(data));
                    case "unLikeVideo" -> sendBooleanResponse(ClientService.unLikeVideo(data));
                    case "unLikeShort" -> sendBooleanResponse(ClientService.unLikeShort(data));
                    case "unLikeVideoComment" -> sendBooleanResponse(ClientService.unLikeVideoComment(data));
                    case "unLikeShortComment" -> sendBooleanResponse(ClientService.unLikeShortComment(data));
                    case "removeVideoFromPlaylist" -> ClientService.removeVideoFromPlaylist(data);
                    case "removeShortFromPlaylist" -> ClientService.removeShortFromPlaylist(data);

                }
            }

        }
        catch (IOException e){
            System.err.println("| <IO Exception>");
        }
        catch (SQLException e) {
            System.err.println("| <SQLException>");
        } finally {

        }
    }
}