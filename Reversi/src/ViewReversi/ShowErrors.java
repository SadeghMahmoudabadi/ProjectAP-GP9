package ViewReversi;

import javafx.scene.control.Alert;

public enum ShowErrors {
    INVALID_COORDINATES("You cannot place the disk on this Coordinates");


    private final String message;

    ShowErrors(String message) {
        this.message = message;
    }

    public void showMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(this.message);
        alert.show();
    }
}
