package Graphic;

import Model.Database;
import Network.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class UsernameController {
    public TextField usernameText;

    public void okUsernameText(ActionEvent actionEvent) {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String[] input = {"user", "edit", "username", usernameText.getText()};
        if (Client.requestToServer(input)) {
            GraphicPlatoPlayer.staticUsernameLabel.setText(usernameText.getText());
            Stage usernameStage = (Stage) usernameText.getScene().getWindow();
            usernameStage.close();
            Database.updateFiles();
        }
    }
}
