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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreatesEvent {
    public DatePicker startsDate;
    public DatePicker endsDate;
    public ChoiceBox gameChoiceBox;
    public TextField coinsEvents;
    public Button createEvents;
    public ImageView backCreates;
    public static String eventCoin;
    public static long eventDaysLeft;
    public static long eventHoursLeft;
    ObservableList<String> gameChoiceBar = FXCollections.observableArrayList("Dots & Boxes", "Reversi");

    @FXML
    public void initialize() {
        gameChoiceBox.setValue("Dots & Boxes");
        gameChoiceBox.setItems(gameChoiceBar);
    }

    public void backEvents(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Stage stage = (Stage) backCreates.getScene().getWindow();
        stage.close();
        Parent parent = FXMLLoader.load(getClass().getResource("platoForAdmin.fxml"));
        Scene scene = new Scene(parent, 372, 523);
        Stage root = new Stage();
        root.setScene(scene);
        root.show();
    }


    public void createEvent(ActionEvent actionEvent) throws ParseException, IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String gameName = (String) gameChoiceBox.getValue();
        LocalDate endEvent = endsDate.getValue();
        eventDaysLeft = ChronoUnit.DAYS.between(LocalDate.now(), endEvent);
        if (eventDaysLeft == 0) {
            eventHoursLeft = ChronoUnit.HOURS.between(LocalDateTime.now(), endEvent);
        }
        eventCoin = coinsEvents.getText();
        if (gameName.equalsIgnoreCase("Dots & Boxes")) {
            String[] input = {"add", "event", "Dots & Boxes", startsDate.getValue().toString(), endsDate.getValue().toString(), eventCoin};
            if (Controller.adminMenu(Admin.getCurrentAdmin().getUserID(), input)) {
                Parent root = FXMLLoader.load(getClass().getResource("dotsEvent.fxml"));
                Scene scene = new Scene(root, 470, 224);
                Stage stage = new Stage();
                stage.setScene(scene);
            }
        } else if (gameName.equalsIgnoreCase("Reversi")) {
            String[] input = {"add", "event", "reversi", startsDate.getValue().toString(), endsDate.getValue().toString(), eventCoin};
            if (Controller.adminMenu(Admin.getCurrentAdmin().getUserID(), input)) {
                Parent root = FXMLLoader.load(getClass().getResource("ReversiEvent.fxml"));
                Scene scene = new Scene(root, 470, 224);
                Stage stage = new Stage();
                stage.setScene(scene);
            }
        }
    }
}
