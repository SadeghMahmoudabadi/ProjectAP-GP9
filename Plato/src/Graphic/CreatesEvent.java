package Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatesEvent {
    public DatePicker startsDate;
    public DatePicker endsDate;
    public ChoiceBox gameChoiceBox;
    public TextField coinsEvents;
    public Button createEvents;
    public ImageView backCreates;

    public void backEvents(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) backCreates.getScene().getWindow();
        stage.close();
        Parent parent = FXMLLoader.load(getClass().getResource("AdminPlatoTab.fxml"));
        Scene scene = new Scene(parent, 372, 523);
        Stage root = new Stage();
        root.setScene(scene);
        root.show();
    }

    public void createEvent(ActionEvent actionEvent) {

    }
}
