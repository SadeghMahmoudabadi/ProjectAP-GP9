package Model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String game;
    private Date startDate;
    private Date endDate;
    private int score;
    private int eventID;
    private ArrayList<Integer> joinedPlayersID;
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<Integer> eventIDs = new ArrayList<>();

    {
        joinedPlayersID = new ArrayList<>();
    }


    public Event(String game,Date startDate, Date endDate, int score, int eventID) {
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        this.score = score;
        this.eventID = eventID;
    }

    public static void addEvent(Event event) {
        events.add(event);
        eventIDs.add(event.getEventID());
        Database.updateFiles();
    }

    public int getEventID() {
        return eventID;
    }

    public void joinEvent(int playerID) {
        this.joinedPlayersID.add(playerID);
        Database.updateFiles();
    }

    public void leftEvent(int playerID) {
        this.joinedPlayersID.remove(playerID);
        Database.updateFiles();
    }

    public boolean editEvent(String field, String newValue) throws ParseException {
        if (field.equalsIgnoreCase("game")) {
            this.game = newValue;
            Database.updateFiles();
            return true;
        } else if (field.equalsIgnoreCase("startDate")) {
            this.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(newValue);
            Database.updateFiles();
            return true;
        } else if (field.equalsIgnoreCase("endDate")) {
            this.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(newValue);
            Database.updateFiles();
            return true;
        } else if (field.equalsIgnoreCase("score")) {
            this.score = Integer.parseInt(newValue);
            Database.updateFiles();
            return true;
        } else {
            //Error     فیلد ایونت وجود ندارد
            return false;
        }
    }

    public static boolean deleteEvent(int eventID) {
        if (eventIDs.contains(eventID)) {
            Event event = findEvent(eventID);
            events.remove(event);
            eventIDs.remove(event.getEventID());
            Database.updateFiles();
            return true;
        } else {
            //Error     ایونت وجود ندارد
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
