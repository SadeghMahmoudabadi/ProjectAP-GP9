package Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AdminEditEvents {
    public Button backEditEvents;
    public Button goID;
    public TextField gameIDTextField;

    public void backEditEvents(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Stage stage = (Stage) backEditEvents.getScene().getWindow();
        stage.close();
        Parent parent = FXMLLoader.load(getClass().getResource("platoForAdmin.fxml"));
        Scene scene = new Scene(parent, 372, 523);
        Stage root = new Stage();
        root.setScene(scene);
        root.show();
    }

    public void goToEvent(ActionEvent actionEvent) {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
