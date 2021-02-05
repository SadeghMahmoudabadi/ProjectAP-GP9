package Graphic;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class DotsGameEvent {
    public JFXButton joinEvent;
    public JFXButton cancelButton;

    public void startDotsEvent(ActionEvent actionEvent) {
    }

    public void cancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
