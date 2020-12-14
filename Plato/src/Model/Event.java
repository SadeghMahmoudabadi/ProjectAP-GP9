package Model;

import java.util.*;

public class Event {
    private String game;
    private Date startDate;
    private Date endDate;
    private int score;
    private String eventID;
    private ArrayList<Player> joinedPlayers;
    private ArrayList<Event> events = new ArrayList<>();

    {
        joinedPlayers = new ArrayList<>();
    }


    /*public Event() {

    }*/

    public static void addEvent() {

    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static void deleteEvent(Event event) {

    }

    public void joinEvent(Player player) {

    }

    public void leftEvent(Player player) {

    }
}
