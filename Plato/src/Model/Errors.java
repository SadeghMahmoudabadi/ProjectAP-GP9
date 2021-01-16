package Model;

import javafx.scene.control.Alert;

public enum Errors {
    USER_INCORRECT_FORMAT("Username format is invalid!"),
    PASS_INCORRECT_FORMAT("Password format is invalid!"),
    FIRSTNAME_INCORRECT_FORMAT("Firstname format is invalid!"),
    LASTNAME_INCORRECT_FORMAT("Lastname format is invalid!"),
    EMAIL_INCORRECT_FORMAT("Email format is invalid!"),
    PHONE_NUMBER_INCORRECT_FORMAT("Phone number format is invalid!"),
    EXIST_USERNAME("There is an user with this username!"),
    LOGOUT_NOT_OCCURRED("An error occurred in logging out; Try again later."),
    USER_OR_PASS_DOES_NOT_EXIST("Incorrect user or password!");

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
