package Controller;

import ControllerDotBox.ControllerDotAndBox;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Controller {

    public static boolean logisterMenu(String[] input) {
        if (input.length == 7 && input[0].equalsIgnoreCase("register")) {
            String firstname = input[3];
            String lastname = input[4];
            String username = input[1];
            String password = input[2];
            String email = input[5];
            String phoneNumber = input[6];
            if (Tools.getIDs().isEmpty()) {
                return Admin.register(firstname, lastname, username, password, email, phoneNumber);
            } else {
                return Player.register(firstname, lastname, username, password, email, phoneNumber);
            }
        } else if (input.length == 3 && input[0].equalsIgnoreCase("login")) {
            String username = input[1];
            String password = input[2];
            if (Tools.checkFormat("username", username) && Tools.checkFormat("password", password)) {
                if (Tools.isUsernameExist(username)) {
                    if (Tools.isAdmin(username)) {
                        return Admin.loginAdmin(username, password);
                    } else {
                        return Player.loginPlayer(username, password);
                    }
                } else {
                    return false;
                }
            }
        } else if (input.length == 3 && input[0].equalsIgnoreCase("delete")) {
            String username = input[1];
            String password = input[2];
            if (Tools.checkFormat("username", username) && Tools.checkFormat("password", password)) {
                if (Tools.isUsernameExist(username)) {
                    if (Tools.isAdmin(username)) {
                        return Admin.deleteAdmin(username, password);
                    } else {
                        return Player.deletePlayer(username, password);
                    }
                } else {
                    Errors.THIS_USER_DOES_NOT_EXIST.showMessage();
                    return false;
                }
            } else {
                Errors.USER_PASS_INCORRECT_FORMAT.showMessage();
                return false;
            }
        } else {
            //Error
            return false;
        }
        return false;
    }

    public static boolean userMenu(int userID, String[] input) {
        if (input.length == 4 && input[0].equalsIgnoreCase("change")
                && input[1].equalsIgnoreCase("password")) {
            String currentPassword = input[2];
            String newPassword = input[3];
            if (Tools.isAdmin(userID)) {
                Admin currentAdmin = Admin.findAdmin(userID);
                return currentAdmin.changePassword(currentPassword, newPassword);
            } else {
                Player currentPlayer = Player.findPlayer(userID);
                return currentPlayer.changePassword(currentPassword, newPassword);
            }
        } else if (input.length == 3 && input[0].equalsIgnoreCase("edit")) {
            String field = input[1];
            String newValue = input[2];
            if (Tools.isUserExist(userID)) {
                if (Tools.isAdmin(userID)) {
                    Admin currentAdmin = Admin.findAdmin(userID);
                    return currentAdmin.editField(field, newValue);
                } else {
                    Player currentPlayer = Player.findPlayer(userID);
                    return currentPlayer.editField(field, newValue);
                }
            } else {
                Errors.ID_DOES_NOT_EXIST.showMessage();
                return false;
            }
        } else if (input.length == 1 && input[0].equalsIgnoreCase("logout")) {
            if (Tools.isAdmin(userID)) {
                return Admin.logout(userID);
            } else {
                return Player.logout(userID);
            }
        } else {
            return false;
        }
    }

    public static boolean playerMenu(int playerID, String[] input) throws IOException {
        Player currentPlayer = Player.findPlayer(playerID);
        if (input.length == 3 && input[0].equalsIgnoreCase("add")
                && input[1].equalsIgnoreCase("friend")) {
            int friendID = Integer.parseInt(input[2]);
            return Tools.sendFriendRequest(playerID, friendID);
        } else if (input.length == 2 && input[0].equalsIgnoreCase("game")   /*+*****************/
                && input[1].equalsIgnoreCase("history")) {
            return true;
        } else if (input.length == 3 && input[0].equalsIgnoreCase("game")   /*+*****************/
                && input[1].equalsIgnoreCase("statistic")) {
            String gameName = input[2];
            return true;
        } else if (input.length == 2 && input[0].equalsIgnoreCase("accept")) {
            int friendID = Integer.parseInt(input[1]);
            return currentPlayer.acceptFriend(friendID);
        } else if (input.length == 2 && input[0].equalsIgnoreCase("decline")) {
            int friendID = Integer.parseInt(input[1]);
            return currentPlayer.declineFriend(friendID);
        } else if (input.length == 2 && input[0].equalsIgnoreCase("remove")) {
            int friendID = Integer.parseInt(input[1]);
            return currentPlayer.removeFriend(friendID);
        } else if (input.length == 3 && input[0].equalsIgnoreCase("add")
                && input[1].equalsIgnoreCase("favorite")) {
            String gameName = input[2];
            return currentPlayer.addFavoriteGame(gameName);
        } else if (input.length == 3 && input[0].equalsIgnoreCase("remove")
                && input[1].equalsIgnoreCase("favorite")) {
            String gameName = input[2];
            return currentPlayer.removeFavoriteGame(gameName);
        } else if (input.length == 2 && input[0].equalsIgnoreCase("delete") && input[1].equalsIgnoreCase("account")) {
            return Player.deletePlayer(currentPlayer.getUsername(), currentPlayer.getPassword());
        } else if (input.length == 3 && input[0].equalsIgnoreCase("edit") && input[1].equalsIgnoreCase("bio")) {
            String bio = input[2];
            Player.getCurrentPlayer().setBio(bio);
            return true;
        } else if (input.length == 3 && input[0].equalsIgnoreCase("edit") && input[1].equalsIgnoreCase("profile")) {
            int profilePicture = Integer.parseInt(input[2]);
            Player.getCurrentPlayer().setProfile(profilePicture);
            return true;
        } else if (input.length == 4 && input[0].equalsIgnoreCase("play") && input[2].equalsIgnoreCase("with")) {
            String gameName = input[1];
            String username = input[4];
            if (!Tools.isUsernameExist(username) || username.equalsIgnoreCase(currentPlayer.getUsername())) {
                Errors.EXIST_USERNAME.showMessage();
                return false;
            }
            if (gameName.equalsIgnoreCase("dotsAndBoxes")) {
                ControllerDotAndBox.setPlayers(currentPlayer, Player.getComponentPlayer());
                Parent root = FXMLLoader.load(Controller.class.getResource("dotsAndBoxes.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root, 1000, 1000);
                stage.setScene(scene);
                stage.show();
                return true;
            } else if (gameName.equalsIgnoreCase("reversi")) {
//                ControllerReversi.setPlayers(currentPlayer, Player.getComponentPlayer());
                Parent root = FXMLLoader.load(Controller.class.getResource("reversi.fxml"));
                Scene scene = new Scene(root, 1000, 1000);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                return true;
            } else {
                //Error
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean adminMenu(int adminID, String[] input) throws ParseException {
        Admin currentAdmin = Admin.findAdmin(adminID);
        if (input.length == 6 && input[0].equalsIgnoreCase("add")
                && input[1].equalsIgnoreCase("event")) {
            String gameName = input[2];
            String[] startDateString = input[3].split("-");
            String[] endDateString = input[4].split("-");
            int startYear = Integer.parseInt(startDateString[0]);
            int startMonth = Integer.parseInt(startDateString[1]);
            int startDay = Integer.parseInt(startDateString[2]);
            int endYear = Integer.parseInt(endDateString[0]);
            int endMonth = Integer.parseInt(endDateString[1]);
            int endDay = Integer.parseInt(endDateString[2]);
            LocalDateTime startDate = LocalDateTime.of(startYear, startMonth, startDay, LocalTime.now().getHour(), LocalTime.now().getMinute());
            LocalDateTime endDate = LocalDateTime.of(endYear, endMonth, endDay, 23, 59);
            int prize = Integer.parseInt(input[5]);
            currentAdmin.addEvent(gameName, startDate, endDate, prize);
            return true;
        } else if (input.length == 5 && input[0].equalsIgnoreCase("edit")
                && input[1].equalsIgnoreCase("event")) {
            int eventID = Integer.parseInt(input[2]);
            String field = input[3];
            String newValue = input[4];
            Event event = Event.findEvent(eventID);
            return event.editEvent(field, newValue);
        } else if (input.length == 5 && input[0].equalsIgnoreCase("remove")
                && input[1].equalsIgnoreCase("event")) {
            int eventID = Integer.parseInt(input[2]);
            return Event.deleteEvent(eventID);
        } else if (input.length == 4 && input[0].equalsIgnoreCase("add")
                && input[1].equalsIgnoreCase("suggestion")) {
            int playerID = Integer.parseInt(input[2]);
            String gameName = input[3];
            return currentAdmin.addSuggestion(playerID, gameName);
        } else if (input.length == 3 && input[0].equalsIgnoreCase("remove")
                && input[1].equalsIgnoreCase("suggestion")) {
            int suggestionID = Integer.parseInt(input[2]);
            Tools.removeSuggestion(suggestionID);
            return true;
        } else if (input.length == 4 && input[0].equalsIgnoreCase("send")
                && input[1].equalsIgnoreCase("message")) {
            int playerID = Integer.parseInt(input[2]);
            String message = input[4];
            Tools.sendMessage(playerID, message);
            return true;
        } else {
            //Error
            return false;
        }
    }

    public static void gameMenu(String[] input) {

    }
}
