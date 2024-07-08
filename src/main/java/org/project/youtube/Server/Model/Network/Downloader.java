package org.project.youtube.Server.Model.Network;

import org.project.youtube.Server.ServerMain;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Downloader implements Runnable {
    private final DataInputStream in;

    public Downloader() throws IOException {
        in = new DataInputStream(ClientHandler.getUserFileTransferSocket().getInputStream());
    }

    @Override
    public void run() {
        try {
            String fileName = in.readUTF();
            String type = in.readUTF();

            int bytes;
            FileOutputStream fileOutputStream;
            if (type.equals("video")) {
                fileOutputStream = new FileOutputStream(ServerMain.VIDEO_PATH + "/" + fileName);
            } else {
                fileOutputStream = new FileOutputStream(ServerMain.SHORT_PATH + "/" + fileName);
            }

            long size = in.readLong();
            byte[] buffer = new byte[32 * 1024];
            while (size > 0 && (bytes = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;
            }
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
