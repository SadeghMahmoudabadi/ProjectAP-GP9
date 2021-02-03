package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Admin extends User {
    private static Admin currentAdmin;
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Integer> adminsID = new ArrayList<>();
    private static ArrayList<Admin> loggedAdmins = new ArrayList<>();
    private static HashMap<Integer, String> messages = new HashMap<>();

    public Admin() {
    }

    public Admin(String firstname, String lastname, String username, String password, String email, String phoneNumber, int userID) {
        super(firstname, lastname, username, password, email, phoneNumber, userID);
    }

    public static Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public void addEvent(String gameName, LocalDateTime startDate, LocalDateTime endDate, int prize) {
        int eventID = Tools.Random();
        Event event = new Event(gameName, startDate, endDate, prize, eventID);
        Event.addEvent(event);
    }

    public boolean addSuggestion(int playerID, String gameName) {
        Player player = Player.findPlayer(playerID);
        if (!player.getSuggestedGames().values().contains(gameName)) {
            Tools.sendSuggestion(playerID, gameName);
            return true;
        } else {
            Errors.EXIST_SUGGESTION.showMessage();
            return false;
        }
    }

    public static void addMessage(int messageID, String message) {
        messages.put(messageID, message);
        Database.updateFiles();
    }

    public void deleteMessages(int messageID) {
        messages.remove(Integer.valueOf(messageID));
        Tools.removeID(messageID);
        Database.updateFiles();
    }

    public static boolean register(String firstname, String lastname, String username, String password, String email, String phoneNumber) {
        if (Tools.checkFormat("name", firstname)) {
            if (Tools.checkFormat("name", lastname)) {
                if (Tools.checkFormat("username", username)) {
                    if (Tools.checkFormat("password", password)) {
                        if (Tools.checkFormat("email", email)) {
                            if (Tools.checkFormat("phoneNumber", phoneNumber)) {
                                int ID = Tools.Random();
                                Admin admin = new Admin(firstname, lastname, username, password, email, phoneNumber, ID);
                                addAdmin(admin);
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

    public static boolean loginAdmin(String username, String password) {
        for (Admin admin : admins) {
            if (admin.getUsername().equalsIgnoreCase(username)) {
                if (admin.getPassword().equals(password)) {
                    login(admin.getUserID());
                    currentAdmin = admin;
                    return true;
                } else {
                    Errors.WRONG_PASSWORD.showMessage();
                    return false;
                }
            }
        }
        Errors.DOES_NOT_EXIST_ADMIN.showMessage();
        return false;
    }

    public static void login(int userID) {
        if (adminsID.contains(userID)) {
            loggedAdmins.add(findAdmin(userID));
        } else {
            Errors.DOES_NOT_EXIST_ADMIN_USERNAME.showMessage();
        }
    }

    public static boolean logout(int userID) {
        if (adminsID.contains(userID)) {
            loggedAdmins.remove(findAdmin(userID));
            currentAdmin = null;
            return true;
        } else {
            //Error     این ادمین لاگین نیست
            return false;
        }
    }

    public static void addAdmin(Admin admin) {
        admins.add(admin);
        adminsID.add(admin.getUserID());
        Database.updateFiles();
    }

    public static boolean deleteAdmin(String username, String password) {
        for (Admin admin : admins) {
            if (admin.getUsername().equalsIgnoreCase(username)) {
                if (admin.getPassword().equals(password)) {
                    deleteAccount(admin);
                    return true;
                } else {
                Errors.WRONG_PASSWORD.showMessage();
                    return false;
                }
            }
        }
        Errors.ADMIN_DELETED.showMessage();
        return false;
    }

    public static void deleteAccount(Admin admin) {
        admins.remove(admin);
        adminsID.remove(Integer.valueOf(admin.getUserID()));
        Tools.removeID(admin.getUserID());
        Database.updateFiles();
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        Admin.admins = admins;
    }

    public static void setAdminsID() {
        for (Admin admin : admins) {
            adminsID.add(admin.getUserID());
        }
    }

    public static Admin findAdmin(int adminID) {
        for (Admin admin : admins) {
            if (admin.getUserID() == adminID) {
                return admin;
            }
        }
        return null;
    }

    public static void setMessages() {
        for (Player player : Player.getPlayers()) {
            messages.putAll(player.getMessages());
        }
    }
}
