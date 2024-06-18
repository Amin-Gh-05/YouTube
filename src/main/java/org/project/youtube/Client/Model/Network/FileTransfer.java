package org.project.youtube.Client.Model.Network;

import org.project.youtube.Server.Model.Network.Uploader;

public class FileTransfer {
    public static void sendFile(String path) {
        new Thread(new Uploader(path)).start();
    }

    public static void getFile() {
        new Thread(new Downloader()).start();
    }
}
