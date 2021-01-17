package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Player extends User {
    private Date passedDays;
    private int coin;
    private int wins;
    private String lastGamePlayed;
    private String bio;
    private int dotAndBoxPlayedNum;
    private int dotAndBoxLevel;
    private int dotAndBoxWins;
    private int reversiPlayedNum;
    private int reversiLevel;
    private int reversiWins;
    private ArrayList<String> favoriteGames;
    private ArrayList<GameLog> gameLogs;
    private ArrayList<Player> friends;
    private ArrayList<Integer> friendRequests;
    private ArrayList<Event> events;
    private ArrayList<String> suggestedGames;
    private HashMap<Integer, String> messages;
    private ArrayList<String> gameHistories;
    private HashMap<String, String> gameStatistics;
    private static ArrayList<Integer> playersID = new ArrayList<>();
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Player> loggedPlayers = new ArrayList<>();
    private static Player currentPlayer;
    private static Player componentPlayer;

    {
        coin = 0;
        wins = 0;
        bio = "Bio...";
        dotAndBoxPlayedNum = 0;
        dotAndBoxLevel = 0;
        dotAndBoxWins = 0;
        reversiPlayedNum = 0;
        reversiLevel = 0;
        reversiWins = 0;
        favoriteGames = new ArrayList<>();
        gameLogs = new ArrayList<>();
        friends = new ArrayList<>();
        friendRequests = new ArrayList<>();
        events = new ArrayList<>();
        suggestedGames = new ArrayList<>();
        messages = new HashMap<>();
        gameHistories = new ArrayList<>();
        gameStatistics = new HashMap<>();
    }

    public Player() {
    }

    public Player(String firstname, String lastname, String username, String password, String email, String phoneNumber, int userID) {
        super(firstname, lastname, username, password, email, phoneNumber, userID);
    }

    public static void addPlayer(Player player) {
        players.add(player);
        playersID.add(player.getUserID());
        Database.updateFiles();
    }

    public static void removePlayer(Player player) {
        players.remove(player);
        playersID.remove(player.getUserID());
        Database.updateFiles();
    }

    public void joinEvent(int eventID) {
        Event.join(eventID, this.getUserID());
        Database.updateFiles();
    }

    public boolean acceptFriend(int friendID) {
        if (this.friendRequests.contains(friendID)) {
            Player friend = findPlayer(friendID);
            this.friends.add(friend);
            this.friendRequests.remove(friendID);
            return true;
        } else {
            //Error
            return false;
        }
    }

    public boolean declineFriend(int friendID) {
        if (this.friendRequests.contains(friendID)) {
            this.friendRequests.remove(friendID);
            return true;
        } else {
            //Error
            return false;
        }
    }

    public boolean removeFriend(int friendID) {
        for (Player friend : friends) {
            if (friend.getUserID() == friendID) {
                this.friends.remove(friend);
                return true;
            }
        }
        //Error
        return false;
    }

    public boolean addFavoriteGame(String gameName) {
        if (this.favoriteGames.contains(gameName)) {
            //Error
            return false;
        } else {
            favoriteGames.add(gameName);
            return true;
        }
    }

    public boolean removeFavoriteGame(String gameName) {
        if (this.favoriteGames.contains(gameName)) {
            favoriteGames.remove(gameName);
            return true;
        } else {
            //Error
            return false;
        }
    }

    public void addWins(int wins) {
        this.wins += wins;
    }

    public void addMoney(int coin) {
        this.coin += coin;
    }

    public void addSuggestedGame(String gameName) {
        suggestedGames.add(gameName);
    }

    public void addMessage(int messageID, String message) {
        messages.put(messageID, message);
    }

    public boolean addFriendRequest(int request) {
        if (!friendRequests.contains(request)) {
            this.friendRequests.add(request);
            Database.updateFiles();
            return true;
        } else {
            //Error
            return false;
        }
    }

    public void deleteMessage(int messageID) {
        messages.remove(messageID);
    }

    public static Player findPlayer(int userID) {
        for (Player player : players) {
            if (player.getUserID() == userID) {
                return player;
            }
        }
        return null;
    }

    public static Player findPlayer(String username) {
        for (Player player : players) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                return player;
            }
        }
        return null;
    }

    public static boolean register(String firstname, String lastname, String username, String password, String email, String phoneNumber) {
        if (Tools.checkFormat("name", firstname)) {
            if (Tools.checkFormat("name", lastname)) {
                if (Tools.checkFormat("username", username)) {
                    if (Tools.checkFormat("password", password)) {
                        if (Tools.checkFormat("email", email)) {
                            if (Tools.checkFormat("phoneNumber", phoneNumber)) {
                                int ID = Tools.Random();
                                Player player = new Player(firstname, lastname, username, password, email, phoneNumber, ID);
                                addPlayer(player);
                                System.out.println("Congrats! You are a player :)");
                                return true;
                            } else {
                                Errors.PHONE_NUMBER_INCORRECT_FORMAT.showMessage();
                                return false;
                            }
                        } else {
                            Errors.EMAIL_INCORRECT_FORMAT.showMessage();
                            return false;
                        }
                    } else {
                        Errors.PASS_INCORRECT_FORMAT.showMessage();
                        return false;
                    }
                } else {
                    Errors.USER_INCORRECT_FORMAT.showMessage();
                    return false;
                }
            } else {
                Errors.LASTNAME_INCORRECT_FORMAT.showMessage();
                return false;
            }
        } else {
            Errors.FIRSTNAME_INCORRECT_FORMAT.showMessage();
            return false;
        }
    }

    public static boolean loginPlayer(String username, String password) {
        for (Player player : players) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                if (player.getPassword().equals(password)) {
                    login(player.getUserID());
                    return true;
                } else {
                    //Error
                    return false;
                }
            }
        }
        //Error
        return false;
    }

    public static void login(int userID) {
        if (playersID.contains(userID)) {
            loggedPlayers.add(findPlayer(userID));
            currentPlayer = findPlayer(userID);
        } else {
            //Error
        }
    }

    public static boolean logout(int userID) {
        if (playersID.contains(userID)) {
            loggedPlayers.remove(findPlayer(userID));
            currentPlayer = null;
            return true;
        } else {
            //Error
            return false;
        }
    }

    public static boolean deletePlayer(String username, String password) {
        for (Player player : players) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                if (player.getPassword().equals(password)) {
                    deleteAccount(player);
                    return true;
                } else {
                    //Error
                    return false;
                }
            }
        }
        //Error
        return false;
    }

    public static void deleteAccount(Player player) {
        players.remove(player);
        playersID.remove(player.getUserID());
        Database.updateFiles();
    }

    public void incrementGamePlayedCount(String gameName) {

        this.findGameLog(gameName).incrementGamePlayedCount();
    }

    public void incrementDotAndBoxPlayedNum() {
        this.dotAndBoxPlayedNum++;
        Database.updateFiles();
    }

    private void incrementWins() {
        this.wins++;
    }

    public void incrementDotAndBoxLevel() {
        this.dotAndBoxLevel++;
        Database.updateFiles();
    }

    public void incrementDotAndBoxWins() {
        this.dotAndBoxWins++;
        this.incrementWins();
        Database.updateFiles();
    }

    public void incrementReversiPlayedNum() {
        this.dotAndBoxPlayedNum++;
        Database.updateFiles();
    }

    public void incrementReversiLevel() {
        this.dotAndBoxLevel++;
        Database.updateFiles();
    }

    public void incrementReversiWins() {
        this.dotAndBoxWins++;
        Database.updateFiles();
    }

    public void addScore(String gameName, int score) {
        try {
            this.findGameLog(gameName).addScore(score);
        } catch (NullPointerException e) {
            //Error
        }
    }

    public ArrayList<String> getSuggestedGames() {
        return suggestedGames;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public HashMap<Integer, String> getMessages() {
        return messages;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getDotAndBoxPlayedNum() {
        return dotAndBoxPlayedNum;
    }

    public int getDotAndBoxLevel() {
        return dotAndBoxLevel;
    }

    public int getDotAndBoxWins() {
        return dotAndBoxWins;
    }

    public int getReversiPlayedNum() {
        return reversiPlayedNum;
    }

    public int getReversiLevel() {
        return reversiLevel;
    }

    public int getReversiWins() {
        return reversiWins;
    }

    public String getBio() {
        return bio;
    }

    public Date getPassedDays() {
        return passedDays;
    }

    public int getCoin() {
        return coin;
    }

    public int getWins() {
        return wins;
    }

    public String getLastGamePlayed() {
        return lastGamePlayed;
    }

    public ArrayList<String> getFavoriteGames() {
        return favoriteGames;
    }

    public ArrayList<GameLog> getGameLogs() {
        return gameLogs;
    }

    public ArrayList<Player> getFriends() {
        return friends;
    }

    public ArrayList<Integer> getFriendRequests() {
        return friendRequests;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<String> getGameHistories() {
        return gameHistories;
    }

    public HashMap<String, String> getGameStatistics() {
        return gameStatistics;
    }

    public static ArrayList<Integer> getPlayersID() {
        return playersID;
    }

    public static ArrayList<Player> getLoggedPlayers() {
        return loggedPlayers;
    }

    public static Player getComponentPlayer() {
        return componentPlayer;
    }

    public static void setComponentPlayer(Player componentPlayer) {
        Player.componentPlayer = componentPlayer;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Player.currentPlayer = currentPlayer;
    }

    public GameLog findGameLog(String gameName) {
        for (GameLog gameLog : this.gameLogs) {
            if (gameLog.getGameName().equalsIgnoreCase(gameName)) {
                return gameLog;
            }
        }
        return null;
    }

    public void setLastGamePlayed(String lastGamePlayed) {
        this.lastGamePlayed = lastGamePlayed;
    }

    public static void setPlayers(ArrayList<Player> players) {
        Player.players = players;
    }

    public static void setPlayersID() {
        for (Player player : players) {
            playersID.add(player.getUserID());
        }
    }
}