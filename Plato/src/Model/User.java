package Model;

import java.util.ArrayList;
import View.Errors;

public class User {
    private String name;
    private String lastName;
    private String username;
    private String userID;
    private String password;
    private String email;
    private String phoneNumber;
    private static ArrayList<User> loggedUsers = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();

    /*public User() {

    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean changePassword(String currentPassword, String newPassword) {
        if (currentPassword.equals(this.password)) {
            setPassword(newPassword);
            return true;
        } else {
            Errors.showErrors(1);
            return false;
        }
    }

    public void editField(String field, String newValue) {
        if (field.equalsIgnoreCase("name")) {
            if (newValue.matches("[a-zA-z]+")) {
                setName(newValue);
            } else {
                Errors.showErrors(2);
            }
        } else if (field.equalsIgnoreCase("lastName")) {
            if (newValue.matches("[a-zA-z]+")) {
                setLastName(newValue);
            } else {
                Errors.showErrors(2);
            }
        } else if (field.equalsIgnoreCase("username")) {
            if (newValue.matches("[a-zA-z0-9]+")) {
                setUsername(newValue);
            } else {
                Errors.showErrors(4);
            }
        } else if (field.equalsIgnoreCase("email")) {
            if (newValue.matches("[a-zA-z0-9]+[@][a-z]+[.][a-z]")) {
                setEmail(newValue);
            } else {
                Errors.showErrors(5);
            }
        } else if (field.equalsIgnoreCase("phoneNumber")) {
            if (newValue.matches("[0-9]+")) {
                setPhoneNumber(newValue);
            } else {
                Errors.showErrors(3);
            }
        }
    }

    public static void login(User user) {

    }

    public static void logout(User user) {

    }
}
