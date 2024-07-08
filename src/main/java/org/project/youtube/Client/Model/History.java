package org.project.youtube.Client.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class History {
    public static List<History> history = new ArrayList<>();

    UUID videoId;
    int time;

    public History(UUID videoId, int time) {
        this.videoId = videoId;
        this.time = time;
    }

    static void serializeHistory() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("history.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(history);
        out.close();
        fileOut.close();

        System.out.println("| history was serialized");
    }

    static void deserializeHistory() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("history.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        history = (ArrayList<History>) in.readObject();
        in.close();
        fileIn.close();

        System.out.println("| history was deserialized");
    }
}
