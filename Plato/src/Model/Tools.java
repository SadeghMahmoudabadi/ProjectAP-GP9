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
            IDs.remove(Integer.valueOf(ID));
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

    public static void sendSuggestion(int receiverID, String gameName) {
        Player receiver = Player.findPlayer(receiverID);
        int messageID = Tools.Random();
        String suggestion = String.format("I suggest you %s", gameName);
        receiver.addMessage(messageID, suggestion);
        receiver.addSuggestedGame(messageID, gameName);
        Database.updateFiles();
    }

    public static void removeSuggestion(int suggestionID) {
        Player.suggestedPlayer(suggestionID).deleteSuggestion(suggestionID);
        Admin.getCurrentAdmin().deleteMessages(suggestionID);
        removeID(suggestionID);
    }

    public static void sendMessage(int receiverID, String message) {
        System.out.println("Send message tools");
        Player receiver = Player.findPlayer(receiverID);
        int messageID = Tools.Random();
        receiver.addMessage(messageID, message);
        Database.updateFiles();
    }

    public static boolean sendFriendRequest(int senderID, int receiverID) {
        Player receiver = Player.findPlayer(receiverID);
        return receiver.addFriendRequest(senderID);
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
