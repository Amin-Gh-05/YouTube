package org.project.youtube.Client.Model.Network;

import java.io.IOException;

public class FileTransfer {
    public static void sendFile(String path) {
        new Thread(new Uploader(path)).start();
    }

    public static void getFile() throws IOException {
        new Thread(new Downloader()).start();
    }
}
