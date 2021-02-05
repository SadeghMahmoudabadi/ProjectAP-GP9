package Model;

import Graphic.DotsEventController;
import Graphic.ReversiEventController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String game;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int prize;
    private int eventID;
    private ArrayList<Integer> joinedPlayersID;
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<Integer> eventIDs = new ArrayList<>();

    {
        joinedPlayersID = new ArrayList<>();
    }

    public Event(String game, LocalDateTime startDate, LocalDateTime endDate, int prize, int eventID) {
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prize = prize;
        this.eventID = eventID;
    }

//    public Event(String game, Date startDate, Date endDate, int prize, int eventID) {
//        this.game = game;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.prize = prize;
//        this.eventID = eventID;
//    }

    public static void addEvent(Event event) {
        events.add(event);
        eventIDs.add(event.getEventID());
        Database.updateFiles();
    }

    public int getEventID() {
        return eventID;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }


    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public ArrayList<Integer> getJoinedPlayersID() {
        return joinedPlayersID;
    }

    public void setJoinedPlayersID(ArrayList<Integer> joinedPlayersID) {
        this.joinedPlayersID = joinedPlayersID;
    }

    public static ArrayList<Integer> getEventIDs() {
        return eventIDs;
    }

    public static void setEventIDs(ArrayList<Integer> eventIDs) {
        Event.eventIDs = eventIDs;
    }

    public Scene getEventScene() throws ParseException, IOException {
        Scene scene = null;
        if ((ChronoUnit.SECONDS.between(LocalDateTime.now(), endDate)) < 0) {
            Event.deleteEvent(eventID);
        } else {
            if (this.game.equalsIgnoreCase("Dots & Boxes")) {
                DotsEventController.eventCoin = Integer.toString(this.prize);
                DotsEventController.eventDaysLeft = ChronoUnit.DAYS.between(LocalDateTime.now(), endDate);
                DotsEventController.eventHoursLeft = ChronoUnit.HOURS.between(LocalDateTime.now(), endDate);
                Parent root = FXMLLoader.load(getClass().getResource("dotsEvent.fxml"));
                scene = new Scene(root, 470, 224);
                Stage stage = new Stage();
                stage.setScene(scene);
            } else if (this.game.equalsIgnoreCase("Reversi")) {
                ReversiEventController.eventCoin = Integer.toString(this.prize);
                ReversiEventController.eventDaysLeft = ChronoUnit.DAYS.between(LocalDateTime.now(), endDate);
                ReversiEventController.eventHoursLeft = ChronoUnit.HOURS.between(LocalDateTime.now(), endDate);
                Parent root = FXMLLoader.load(getClass().getResource("reversiEvent.fxml"));
                scene = new Scene(root, 470, 224);
                Stage stage = new Stage();
                stage.setScene(scene);
            }
        }
        return scene;
    }

    public void joinEvent(int playerID) {
        this.joinedPlayersID.add(playerID);
        Database.updateFiles();
    }

    public void leftEvent(int playerID) {
        this.joinedPlayersID.remove(Integer.valueOf(playerID));
        Database.updateFiles();
    }

    public boolean editEvent(String field, String newValue) throws ParseException {
        if (field.equalsIgnoreCase("game")) {
            this.game = newValue;
            Database.updateFiles();
            return true;
        } else if (field.equalsIgnoreCase("startDate")) {
            this.startDate = LocalDateTime.of(2021, 1, 15, 0, 0, 0);
            Database.updateFiles();
            return true;
        } else if (field.equalsIgnoreCase("endDate")) {
            this.endDate = LocalDateTime.of(2021, 1, 18, 0, 0, 0);
            Database.updateFiles();
            return true;
        } else if (field.equalsIgnoreCase("score")) {
            this.prize = Integer.parseInt(newValue);
            Database.updateFiles();
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteEvent(int eventID) {
        if (eventIDs.contains(eventID)) {
            Event event = findEvent(eventID);
            events.remove(event);
            eventIDs.remove(Integer.valueOf(eventID));
            Tools.removeID(event.getEventID());
            Database.updateFiles();
            return true;
        } else {
            return false;
        }

    }

    public static void join(int eventID, int playerID) {
        findEvent(eventID).joinEvent(playerID);
        Database.updateFiles();
    }

    public static void left(int eventID, int playerID) {
        findEvent(eventID).leftEvent(playerID);
        Database.updateFiles();
    }

    public static Event findEvent(int eventID) {
        if (eventIDs.contains(eventID)) {
            for (Event event : events) {
                if (event.getEventID() == eventID) {
                    return event;
                }
            }
        }
        return null;
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static void setEvents(ArrayList<Event> events) {
        Event.events = events;
    }

    public static void setEventIDs() {
        for (Event event : events) {
            eventIDs.add(event.getEventID());
        }
    }
}
