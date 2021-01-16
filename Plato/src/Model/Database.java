package Model;

import ModelReversi.PlayerReversi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Database {

    public static void updateFiles() {
        try {
            String arrayDataPlayer = new Gson().toJson(Player.getPlayers());
            FileWriter fileWriterPlayer = new FileWriter("PlayersData.txt");
            fileWriterPlayer.write(arrayDataPlayer);
            fileWriterPlayer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            String arrayDataAdmin = new Gson().toJson(Admin.getAdmins());
            FileWriter fileWriterAdmin = new FileWriter("AdminsData.txt");
            fileWriterAdmin.write(arrayDataAdmin);
            fileWriterAdmin.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            String arrayDataEvent = new Gson().toJson(Event.getEvents());
            FileWriter fileWriterEvent = new FileWriter("EventsData.txt");
            fileWriterEvent.write(arrayDataEvent);
            fileWriterEvent.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            String arrayDataID = new Gson().toJson(Tools.getIDs());
            FileWriter fileWriterPlayer = new FileWriter("IDsData.txt");
            fileWriterPlayer.write(arrayDataID);
            fileWriterPlayer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readFiles() {
        FileReader fileReaderPlayer = null;
        try {
            fileReaderPlayer = new FileReader("PlayersData.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReaderPlayer = new BufferedReader(fileReaderPlayer);
        try {
            String allPlayersStringForm = bufferedReaderPlayer.readLine().trim();
            Gson gson = new Gson();
            Type playerType = new TypeToken<ArrayList<Player>>() {
            }.getType();
            Player.setPlayers(gson.fromJson(allPlayersStringForm, playerType));
            bufferedReaderPlayer.close();
            fileReaderPlayer.close();
        } catch (IOException | NullPointerException ignored) {
        }
        FileReader fileReaderAdmin = null;
        try {
            fileReaderAdmin = new FileReader("AdminsData.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReaderAdmin = new BufferedReader(fileReaderAdmin);
        try {
            String allAdminsStringForm = bufferedReaderAdmin.readLine().trim();
            Gson gson = new Gson();
            Type adminType = new TypeToken<ArrayList<Admin>>() {
            }.getType();
            Admin.setAdmins(gson.fromJson(allAdminsStringForm, adminType));
            bufferedReaderAdmin.close();
            fileReaderAdmin.close();
        } catch (IOException | NullPointerException ignored) {
        }
        FileReader fileReaderEvent = null;
        try {
            fileReaderEvent = new FileReader("EventsData.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReaderEvent = new BufferedReader(fileReaderEvent);
        try {
            String allEventStringForm = bufferedReaderEvent.readLine().trim();
            Gson gson = new Gson();
            Type eventType = new TypeToken<ArrayList<Event>>() {
            }.getType();
            Event.setEvents(gson.fromJson(allEventStringForm, eventType));
            bufferedReaderEvent.close();
            fileReaderEvent.close();
        } catch (IOException | NullPointerException ignored) {
        }
        FileReader fileReaderID = null;
        try {
            fileReaderID = new FileReader("IDsData.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReaderID = new BufferedReader(fileReaderID);
        try {
            String allIDsStringForm = bufferedReaderID.readLine().trim();
            Gson gson = new Gson();
            Type IDType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            Tools.setIDs(gson.fromJson(allIDsStringForm, IDType));
            bufferedReaderID.close();
            fileReaderID.close();
        } catch (IOException | NullPointerException ignored) {
        }
        Player.setPlayersID();
        Admin.setAdminsID();
        Event.setEventIDs();
        Admin.setMessages();
    }
}
