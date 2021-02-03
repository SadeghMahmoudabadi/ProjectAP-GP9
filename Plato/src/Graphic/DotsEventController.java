package Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DotsEventController implements Initializable {
    public Button openJoinDotBox;
    public Label eventPrize;
    public static String eventCoin;
    public static long eventDaysLeft;
    public static long eventHoursLeft;
    public Button joinDotsEvent;
    public Label leftLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventPrize.setText(eventCoin);
        if (eventDaysLeft == 0) {
            leftLabel.setText(eventHoursLeft + " H");
        } else {
            leftLabel.setText(eventDaysLeft + " D");
        }
    }

    public void joinEvent(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root= FXMLLoader.load(getClass().getResource("dotsGameEvent.fxml"));
        Scene scene= new Scene(root,424,700);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void openJoinDotBox(ActionEvent actionEvent) {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
