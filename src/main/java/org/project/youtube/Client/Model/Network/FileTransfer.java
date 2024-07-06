package org.project.youtube.Client.Model.Network;

import java.io.IOException;
import java.util.UUID;

public class FileTransfer {
    public static void sendFile(String path, UUID ID, String type) {
        new Thread(new Uploader(path, ID, type)).start();
    }

    public static void getFile() throws IOException {
        new Thread(new Downloader()).start();
    }
}
