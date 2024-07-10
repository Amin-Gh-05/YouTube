package org.project.youtube.Client.Model.Network;

import java.io.IOException;
import java.util.UUID;

public class FileTransfer {
    public static Thread sendFile(String path, UUID ID, String type) {
        Thread thread = new Thread(new Uploader(path, ID, type));
        thread.start();
        return thread;
    }

    public static Thread getFile() throws IOException {
        Thread thread = new Thread(new Downloader());
        thread.start();
        return thread;
    }
}
