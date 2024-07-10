package org.project.youtube.Client.Model;

import org.project.youtube.Client.Controller.MainController;
import org.project.youtube.Client.Main;

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

    public static void addNewHistory(UUID videoId) {
        for (History history1 : history) {
            if (history1.videoId.toString().equals(videoId.toString())) {
                return;
            }
        }

        History.history.add(new History(videoId, 0));

        System.out.println("| a new history added");
    }

    public static void updateHistory(UUID id, int t) {
        for (int i = 0; i < History.history.size(); i++) {
            if (History.history.get(i).videoId.toString().equals(id.toString())) {
                History.history.set(i, new History(id, t));
            }
        }

        System.out.println("| a history updated");
    }

    public static int getHistory(UUID id) {
        for (History history1 : history) {
            if (history1.videoId.toString().equals(id.toString())) {
                return history1.time;
            }
        }
        return 0;
    }

    public static void serializeHistory() throws IOException {
        if (MainController.user != null) {
            FileOutputStream fileOut = new FileOutputStream(Main.CASH_PATH + "/" + MainController.user.getYid().toString() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(history);
            out.close();
            fileOut.close();

            System.out.println("| history was serialized");
        }
    }

    public static void deserializeHistory() {
        try {
            FileInputStream fileIn = new FileInputStream(Main.CASH_PATH + "/" + MainController.user.getYid().toString() + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            history = (ArrayList<History>) in.readObject();
            in.close();
            fileIn.close();

            System.out.println("| history was deserialized");
        }
        catch (Exception e) {
            System.out.println("| history not found");
        }
    }

    public UUID getVideoId() {
        return videoId;
    }
}
