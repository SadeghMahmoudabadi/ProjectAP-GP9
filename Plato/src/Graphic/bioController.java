package Graphic;

import Model.Database;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class bioController implements Initializable {

    @FXML
    public TextField bioText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void okBioText(ActionEvent actionEvent) {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        GraphicPlatoPlayer.staticBioLabel.setText(bioText.getText());
        Player.getCurrentPlayer().setBio(bioText.getText());
        Stage bioStage = (Stage) bioText.getScene().getWindow();
        bioStage.close();
        Database.updateFiles();
    }
}
