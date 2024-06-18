package org.project.youtube.Server.Model.Network;

public class FileTransfer {
    public static void sendFile(String path) {
        new Thread(new Uploader(path)).start();
    }
}
