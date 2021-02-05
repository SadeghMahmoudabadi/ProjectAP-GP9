package Graphic;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class ReversiGameEvent {
    public JFXButton joinEventReversi;
    public JFXButton cancelButton;

    public void cancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void joinEventReversi(ActionEvent actionEvent) {
    }
}