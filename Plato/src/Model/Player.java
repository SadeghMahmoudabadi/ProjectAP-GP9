package Model;

import java.util.ArrayList;
import java.util.Date;

public class Player extends User {
    private Date passedDays;
    private int money;
    private int score;
    private ArrayList<LoginGame> loggedGames;
    private ArrayList<Player> friends;
    private ArrayList<String> friendRequests;

    {
        money = 0;
        score = 0;
        loggedGames = new ArrayList<>();
        friends = new ArrayList<>();
        friendRequests = new ArrayList<>();
    }

    /*public Player() {

    }*/

    public void joinEvent(int eventID) {

    }

    public void startGame(String gameName) {

    }

    public void addFriend(String username) {

    }

    public void loginGame(String gameName) {

    }

    public void logoutGame(String game) {

    }

    public void addScore(int score) {

    }

    public void addMoney(int money) {

    }
}
