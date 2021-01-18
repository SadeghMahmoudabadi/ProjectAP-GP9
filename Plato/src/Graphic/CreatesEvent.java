package Graphic;

import Controller.Controller;
import Model.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.text.ParseException;
import java.util.Date;

public class CreatesEvent {
    public DatePicker startsDate;
    public DatePicker endsDate;
    public ChoiceBox gameChoiceBox;
    public TextField coinsEvents;
    public Button createEvents;
    public ImageView backCreates;
    public static String eventCoin;
    ObservableList<String> gameChoiceBar = FXCollections.observableArrayList("Dots & Boxes", "Reversi");

    @FXML
    public void initialize() {
        gameChoiceBox.setValue("Dots & Boxes");
        gameChoiceBox.setItems(gameChoiceBar);
    }

    public void backEvents(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) backCreates.getScene().getWindow();
        stage.close();
        Parent parent = FXMLLoader.load(getClass().getResource("platoForAdmin.fxml"));
        Scene scene = new Scene(parent, 372, 523);
        Stage root = new Stage();
        root.setScene(scene);
        root.show();
    }


    public void createEvent(ActionEvent actionEvent) throws ParseException, IOException {
        String gameName = (String) gameChoiceBox.getValue();
        System.out.println(gameName);
        eventCoin = coinsEvents.getText();
        if (gameName.equalsIgnoreCase("Dots & Boxes")) {
            String[] input = {"add", "event", "dotBox", "11/01/2020", "12/01/2020", eventCoin};
            if (Controller.adminMenu(Admin.getCurrentAdmin().getUserID(), input)) {
                Parent root = FXMLLoader.load(getClass().getResource("DotsEvent.fxml"));
                Scene scene = new Scene(root, 470, 224);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        } else if (gameName.equalsIgnoreCase("Reversi")) {
            String[] input = {"add", "event", "reversi", "11/01/2020", "12/01/2020", eventCoin};
            if (Controller.adminMenu(Admin.getCurrentAdmin().getUserID(), input)) {
                Parent root = FXMLLoader.load(getClass().getResource("ReversiEvent.fxml"));
                Scene scene = new Scene(root, 470, 224);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
