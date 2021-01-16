package Model;

import java.util.ArrayList;

public class Tools {
    private static ArrayList<Integer> IDs = new ArrayList<>();

    public static int Random() {
        int min = 1000000;
        int max = 9999999;
        int rand;
        while (true) {
            rand = (int) (Math.random() * (max - min + 1)) + min;
            if (!IDs.contains(rand)) {
                IDs.add(rand);
                Database.updateFiles();
                return rand;
            }
        }
    }

    public static void removeID(int ID) {
        if (IDs.contains(ID)) {
            IDs.remove(ID);
        }
    }

    public static void setIDs(ArrayList<Integer> IDs) {
        Tools.IDs = IDs;
    }

    public static ArrayList<Integer> getIDs() {
        return IDs;
    }

    public static boolean checkFormat(String field, String value) {
        if (field.equalsIgnoreCase("name")) {
            return value.matches("[a-zA-Z]+");
        } else if (field.equalsIgnoreCase("username")) {
            return value.matches("[a-zA-Z0-9]+");
        } else if (field.equalsIgnoreCase("password")) {
            return value.matches("[a-zA-Z0-9!@.#$]+");
        } else if (field.equalsIgnoreCase("email")) {
            return value.matches("[a-zA-Z0-9.]+[@][a-zA-Z]+[.][a-zA-Z]+");
        } else if (field.equalsIgnoreCase("phoneNumber")) {
            return value.matches("[0-9]+");
        }
        return false;
    }

    public static void sendMessage(int senderID, int receiverID, String message) {
        if (isAdmin(senderID)) {
            Admin sender = Admin.findAdmin(senderID);
            Player receiver = Player.findPlayer(receiverID);
            int messageID = Tools.Random();
            //Time of sending message
            message = String.format("Admin %s: %s", sender.getUsername(), message);
            receiver.addMessage(messageID, message);
            Admin.addMessage(messageID, message);
        } else {
            Player sender = Player.findPlayer(senderID);
            Player receiver = Player.findPlayer(receiverID);
            int messageID = Tools.Random();
            //Time of sending message
            message = String.format("Player %s: %s", sender.getUsername(), message);
            receiver.addMessage(messageID, message);
            Admin.addMessage(messageID, message);
        }
    }

    public static void sendFriendRequest(int senderID, int receiverID) {
        Player receiver = Player.findPlayer(receiverID);
        receiver.addFriendRequest(senderID);
    }

    public static boolean isAdmin(int userID) {
        for (Admin admin : Admin.getAdmins()) {
            if (admin.getUserID() == userID) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAdmin(String username) {
        for (Admin admin : Admin.getAdmins()) {
            if (admin.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUsernameExist(String username) {
        for (Admin admin : Admin.getAdmins()) {
            if (admin.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUserExist(int userID) {
        for (Admin admin : Admin.getAdmins()) {
            if (admin.getUserID() == userID) {
                return true;
            }
        }
        for (Player player : Player.getPlayers()) {
            if (player.getUserID() == userID) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailExist(String email) {
        for (Admin admin : Admin.getAdmins()) {
            if (admin.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        for (Player player : Player.getPlayers()) {
            if (player.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPhoneNumberExist(String phoneNumber) {
        for (Admin admin : Admin.getAdmins()) {
            if (admin.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return true;
            }
        }
        for (Player player : Player.getPlayers()) {
            if (player.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}
