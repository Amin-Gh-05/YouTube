package org.project.youtube.Client.Model.Network;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Uploader implements Runnable {
    private DataOutputStream out;
    private String path;

    public Uploader(String path) {
        try {
            out = new DataOutputStream((Client.getFileTransferSocket().getOutputStream()));
            this.path = path;
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

            out.writeUTF(file.getName());
            out.writeLong(file.length());

            while ((bytes = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytes);
                out.flush();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}