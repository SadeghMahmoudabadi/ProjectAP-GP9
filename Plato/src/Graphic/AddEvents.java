package Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
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
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("Create Events.fxml"));
        Stage addEvent = new Stage();
        addEvent.setScene(new Scene(root, 600, 543));
        addEvent.show();
    }

    public void editEvent(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("admin Edit Event.fxml"));
        Stage addEvent = new Stage();
        addEvent.setScene(new Scene(root, 423, 415));
        addEvent.show();
    }

}
