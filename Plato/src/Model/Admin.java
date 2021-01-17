package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Admin extends User {
    private static Admin currentAdmin;
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Integer> adminsID = new ArrayList<>();
    private static ArrayList<Admin> loggedAdmins = new ArrayList<>();
    private static HashMap<Integer, String> messages = new HashMap<>();

    public Admin() {}

    public Admin(String firstname, String lastname, String username, String password, String email, String phoneNumber, int userID) {
        super(firstname, lastname, username, password, email, phoneNumber, userID);
    }

    public static User getCurrentAdmin() {
        return currentAdmin;
    }

    public void addEvent(String gameName, Date startDate, Date endDate, int score) {
        int eventID = Tools.Random();
        Event event = new Event(gameName,startDate, endDate, score, eventID);
        Event.addEvent(event);
    }

    public boolean addSuggestion(int playerID, String gameName) {
        Player player = Player.findPlayer(playerID);
        if (!player.getSuggestedGames().contains(gameName)) {
            String message = String.format("My suggestion to you is %s", gameName);
            player.addSuggestedGame(gameName);
            Tools.sendMessage(this.getUserID(), playerID, message);
            return true;
        } else {
            //Error     ساجسشن تکراری
            return false;
        }
    }

    public static void addMessage(int messageID, String message) {
        messages.put(messageID, message);
        Database.updateFiles();
    }

    public void deleteMessages(int messageID) {
        messages.remove(messageID);
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
                                System.out.println("Congrats! You are an admin :)");
                                return true;
                            } else {
                                //Error     فرمت شماره
                                return false;
                            }
                        } else {
                            //Error     فرمت ایمیل
                            return false;
                        }
                    } else {
                        //Error     فرمت پسوورد
                        return false;
                    }
                } else {
                    //Error     فرمت یوزرنیم
                    return false;
                }
            } else {
                //Error     فرمت فامیل
                return false;
            }
        } else {
            //Error     فرمت اسم
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
                    //Error     پسوورد غلط
                    return false;
                }
            }
        }
        //Error     ادمین با این یوزرنیم وجود ندارد
        return false;
    }

    public static void login(int userID) {
        if (adminsID.contains(userID)) {
            loggedAdmins.add(findAdmin(userID));
        } else {
            //Error     ادمین با این ایدی وجود ندارد
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
                    //Error     پسوورد اشتباه
                    return false;
                }
            }
        }
        //Error     ادمین وجود ندارد
        return false;
    }

    public static void deleteAccount(Admin admin) {
        admins.remove(admin);
        adminsID.remove(admin.getUserID());
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
