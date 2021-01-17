package Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminEditEvents {
    public Button backEditEvents;
    public Button goID;
    public TextField gameIDTextField;

    public void backEditEvents(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) backEditEvents.getScene().getWindow();
        stage.close();
        Parent parent = FXMLLoader.load(getClass().getResource("platoForAdmin.fxml"));
        Scene scene = new Scene(parent, 372, 523);
        Stage root = new Stage();
        root.setScene(scene);
        root.show();
    }

    public void goToEvent(ActionEvent actionEvent) {

    }
}
