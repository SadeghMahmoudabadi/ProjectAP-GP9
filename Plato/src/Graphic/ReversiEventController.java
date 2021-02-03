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

public class ReversiEventController implements Initializable {
    public Button joinReversiEvent;
    public Label eventPrize;
    public static String eventCoin;
    public static long eventDaysLeft;
    public static long eventHoursLeft;
    public Label leftLabel;

    public void openJoinReversi(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root= FXMLLoader.load(getClass().getResource("reversiGameEvent.fxml"));
        Scene scene= new Scene(root,424,700);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventPrize.setText(eventCoin);
        if (eventDaysLeft == 0) {
            if (eventHoursLeft == 0) {
                leftLabel.setText("Less than 1 hour");
            } else {
                leftLabel.setText(eventHoursLeft + " H");
            }
        } else {
            leftLabel.setText(eventDaysLeft + " D");
        }
    }
}
