package Model;

import javafx.scene.control.Alert;

public enum Errors {
    USER_INCORRECT_FORMAT("Username format is invalid!"),
    PASS_INCORRECT_FORMAT("Password format is invalid!"),
    FIRSTNAME_INCORRECT_FORMAT("Firstname format is invalid!"),
    LASTNAME_INCORRECT_FORMAT("Lastname format is invalid!"),
    EMAIL_INCORRECT_FORMAT("Email format is invalid!"),
    EMAIL_EXIST("This email have already taken!"),
    PHONE_NUMBER_INCORRECT_FORMAT("Phone number format is invalid!"),
    PHONE_NUMBER_EXIST("This phone number is already exist!"),
    EXIST_USERNAME("There is an user with this username!"),
    LOGOUT_NOT_OCCURRED("An error occurred in logging out; Try again later."),
    USER_OR_PASS_DOES_NOT_EXIST("Incorrect user or password!"),
    THIS_USER_DOES_NOT_EXIST("This user does not exist!"),
    USER_PASS_INCORRECT_FORMAT("Username or password format is invalid!"),
    ID_DOES_NOT_EXIST("This ID does not exist!"),
    EXIST_SUGGESTION("This suggestion does exist!"),
    WRONG_PASSWORD("This password is wrong!"),
    DOES_NOT_EXIST_ADMIN("Admin with this name does not exist!"),
    DOES_NOT_EXIST_ADMIN_USERNAME("Admin with this username does not exist!"),
    ADMIN_DELETED("Input admin not exist"),
    EVENT_FIELD("this fields of event does not exist!"),
    EVENT_ID("This event ID does not exist!"),
    REQUEST_FRIENDS("This user did not send request!"),
    FAVORITE_GAME("This game has been your favorite before"),
    REQUEST_ID_AGAIN("You have already requested!")
    ;

    private String message;

    Errors(String message) {
        this.message = message;
    }

    public void showMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(this.message);
        alert.show();
    }
}
