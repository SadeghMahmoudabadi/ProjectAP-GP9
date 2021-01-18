package Graphic;

import Graphic.CreatesEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class dotsEventController implements Initializable {
    public Button openJoinDotBox;
    public Label eventPrize;

    public void openJoinDotBox(ActionEvent actionEvent) {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventPrize.setText(CreatesEvent.eventCoin);
    }
}
