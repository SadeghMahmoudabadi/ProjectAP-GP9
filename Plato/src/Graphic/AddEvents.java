package Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEvents implements Initializable {


    public Button addEvent;
    public Button editEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addEvent(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../../../../Desktop/project (2)/project/PlatoGraphic/src/Graphic/Create Events.fxml"));
        Stage addEvent = new Stage();
        addEvent.setScene(new Scene(root, 600, 543));
        addEvent.show();
    }

    public void editEvent(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin Edit Event.fxml"));
        Stage addEvent = new Stage();
        addEvent.setScene(new Scene(root, 423, 415));
        addEvent.show();
    }

}
