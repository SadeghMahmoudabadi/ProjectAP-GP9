package Model;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Player extends User {
    private int profile;
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
    private LocalDate registerDate;
    private ArrayList<String> favoriteGames;
    private ArrayList<Integer> friends;
    private ArrayList<Integer> friendRequests;
    private ArrayList<Event> events;
    private HashMap<Integer, String> suggestedGames;
    private HashMap<Integer, String> messages;
    private ArrayList<String> gameHistories;
    private HashMap<String, String> gameStatistics;
    private static ArrayList<Integer> playersID = new ArrayList<>();
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Player> loggedPlayers = new ArrayList<>();
    private static ArrayList<Integer> suggestMessagesID = new ArrayList<>();
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
        profile = 0;
        favoriteGames = new ArrayList<>();
        friends = new ArrayList<>();
        friendRequests = new ArrayList<>();
        events = new ArrayList<>();
        suggestedGames = new HashMap<>();
        messages = new HashMap<>();
        gameHistories = new ArrayList<>();
        gameStatistics = new HashMap<>();
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
        Database.updateFiles();
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
        playersID.remove(Integer.valueOf(player.getUserID()));
        Tools.removeID(player.getUserID());
        Database.updateFiles();
    }

    public void joinEvent(int eventID) {
        Event.join(eventID, this.getUserID());
        Database.updateFiles();
    }

    public boolean acceptFriend(int friendID) {
        if (this.friendRequests.contains(friendID)) {
            Player friend = findPlayer(friendID);
            this.addFriend(friendID);
            friend.addFriend(this.getUserID());
            this.friendRequests.remove(Integer.valueOf(friendID));
            Database.updateFiles();
            return true;
        } else {
            return false;
        }
    }

    public void addFriend(int friendID) {
        if (!this.friends.contains(friendID)) {
            this.friends.add(friendID);
        }
    }

    public boolean declineFriend(int friendID) {
        if (this.friendRequests.contains(friendID)) {
            this.friendRequests.remove(Integer.valueOf(friendID));
            Database.updateFiles();
            return true;
        } else {
            return false;
        }
    }

    public boolean removeFriend(int friendID) {
        if (this.friends.contains(friendID)) {
            this.friends.remove(Integer.valueOf(friendID));
            findPlayer(friendID).removeFriend(this.getUserID());
            Database.updateFiles();
            return true;
        }
        return false;
    }

    public boolean addFavoriteGame(String gameName) {
        if (this.favoriteGames.contains(gameName)) {
            return false;
        } else {
            favoriteGames.add(gameName);
            Database.updateFiles();
            return true;
        }
    }

    public boolean removeFavoriteGame(String gameName) {
        if (this.favoriteGames.contains(gameName)) {
            favoriteGames.remove(gameName);
            Database.updateFiles();
            return true;
        } else {
            return false;
        }
    }

    public void addMoney(int coin) {
        this.coin += coin;
        Database.updateFiles();
    }

    public void addSuggestedGame(int ID, String gameName) {
        suggestedGames.put(ID, gameName);
        suggestMessagesID.add(ID);
        Database.updateFiles();
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
            return false;
        }
    }

    public void deleteSuggestion(int suggestionID) {
        this.suggestedGames.remove(Integer.valueOf(suggestionID));
        Tools.removeID(suggestionID);
        this.deleteMessage(suggestionID);
    }

    public void deleteMessage(int messageID) {
        messages.remove(Integer.valueOf(messageID));
        Database.updateFiles();
    }

    public static Player suggestedPlayer(int suggestionID) {
        for (Player player : players) {
            if (player.getSuggestedGames().keySet().contains(suggestionID)) {
                return player;
            }
        }
        return null;
    }

    public static Player receiverPlayer(int messageID) {
        for (Player player : players) {
            if (player.getMessages().keySet().contains(messageID)) {
                return player;
            }
        }
        return null;
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
                    if (Tools.isUsernameExist(username)) {
                        return false;
                    }
                    if (Tools.checkFormat("password", password)) {
                        if (Tools.checkFormat("email", email)) {
                            if (Tools.isEmailExist(email)) {
                                return false;
                            }
                            if (Tools.checkFormat("phoneNumber", phoneNumber)) {
                                if (Tools.isPhoneNumberExist(phoneNumber)) {
                                    return false;
                                }
                                int ID = Tools.Random();
                                Player player = new Player(firstname, lastname, username, password, email, phoneNumber, ID);
                                player.setRegisterDate(LocalDate.now());
                                addPlayer(player);
                                Tools.sendMessage(player.getUserID(), "Welcome to plato ;)");
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
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
                    return false;
                }
            }
        }
        return false;
    }

    public static void login(int userID) {
        if (playersID.contains(userID)) {
            loggedPlayers.add(findPlayer(userID));
            currentPlayer = findPlayer(userID);
        }
    }

    public static boolean logout(int userID) {
        if (playersID.contains(userID)) {
            loggedPlayers.remove(findPlayer(userID));
            currentPlayer = null;
            return true;
        } else {
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
                    return false;
                }
            }
        }
        return false;
    }

    public static void deleteAccount(Player player) {
        players.remove(player);
        playersID.remove(Integer.valueOf(player.getUserID()));
        for (Integer messageID : player.getMessages().keySet()) {
            Tools.removeID(messageID);
        }
        Tools.removeID(player.getUserID());
        Database.updateFiles();
    }

    public void incrementDotAndBoxPlayedNum() {
        this.dotAndBoxPlayedNum++;
        Database.updateFiles();
    }

    private void incrementWins() {
        this.wins++;
    }

    public void incrementDotAndBoxWins() {
        this.dotAndBoxWins++;
        this.incrementWins();
        if (dotAndBoxWins >= 2) {
            this.dotAndBoxLevel = 1;
        }
        if (dotAndBoxWins >= 5) {
            this.dotAndBoxLevel = 2;
        }
        if (dotAndBoxWins >= 10) {
            this.dotAndBoxLevel = 3;
        }
        if (dotAndBoxWins >= 20) {
            this.dotAndBoxLevel = 4;
        }
        if (dotAndBoxWins >= 50) {
            this.dotAndBoxLevel = 5;
        }
        Database.updateFiles();
    }

    public void incrementReversiPlayedNum() {
        this.reversiPlayedNum++;
        Database.updateFiles();
    }

    public void incrementReversiWins() {
        this.reversiWins++;
        this.incrementWins();
        if (reversiWins >= 2) {
            this.reversiLevel = 1;
        }
        if (reversiWins >= 5) {
            this.reversiLevel = 2;
        }
        if (reversiWins >= 10) {
            this.reversiLevel = 3;
        }
        if (reversiWins >= 20) {
            this.reversiLevel = 4;
        }
        if (reversiWins >= 50) {
            this.reversiLevel = 5;
        }
        Database.updateFiles();
    }

    public HashMap<Integer, String> getSuggestedGames() {
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

    public ArrayList<Integer> getFriends() {
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

    public void setLastGamePlayed(String lastGamePlayed) {
        this.lastGamePlayed = lastGamePlayed;
    }

    public static void setPlayers(ArrayList<Player> players) {
        Player.players = players;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public static ArrayList<Integer> getSuggestMessagesID() {
        return suggestMessagesID;
    }

    public static void setPlayersID() {
        for (Player player : players) {
            playersID.add(player.getUserID());
        }
    }
}