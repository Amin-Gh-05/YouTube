package org.project.youtube.Client.Model.Network;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

public class Uploader implements Runnable {
    private final DataOutputStream out;
    private final String path;
    private final String ID;
    private final String type;

    public Uploader(String path, UUID ID, String type) {
        try {
            out = new DataOutputStream((Client.getFileTransferSocket().getOutputStream()));
            this.path = path;
            this.ID = ID.toString();
            this.type = type;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[32 * 1024];
            int bytes;

            String fileName = ID + ".mp4";
            out.writeUTF(fileName);
            out.writeUTF(type);
            out.writeLong(file.length());

            while ((bytes = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytes);
                out.flush();
            }

            fileInputStream.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
