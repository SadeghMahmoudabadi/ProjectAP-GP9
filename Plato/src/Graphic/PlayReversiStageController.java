package Graphic;

import GraphicDotBox.GraphicController;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PlayReversiStageController {
    public ImageView backReversiStage;
    public TextField component;
    public static Player player2;

    public void playReversi(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String player2Name = component.getText();
        player2 = Player.findPlayer(player2Name);
        if (player2 != null) {
            Player.setComponentPlayer(player2);
            GraphicController.startGame();
        }
    }

    public void backReversiStage(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("platoForPlayer.fxml"));
        Scene scene = new Scene(root, 508, 743);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage back = (Stage) backReversiStage.getScene().getWindow();
        back.close();
    }
}
