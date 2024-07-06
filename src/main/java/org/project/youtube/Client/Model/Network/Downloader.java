package org.project.youtube.Client.Model.Network;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Downloader implements Runnable {
    private DataInputStream in;

    public Downloader () throws IOException {
        in = new DataInputStream(Client.getFileTransferSocket().getInputStream());
    }

    @Override
    public void run() {
        try {
            String fileName = in.readUTF();

            int bytes;
            FileOutputStream fileOutputStream = new FileOutputStream("C:/YouTube/" + fileName);

            long size = in.readLong();
            byte[] buffer = new byte[32 * 1024];
            while (size > 0 && (bytes = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;
            }
            fileOutputStream.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
