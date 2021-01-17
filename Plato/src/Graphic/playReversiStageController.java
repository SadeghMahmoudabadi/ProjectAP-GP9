package Graphic;

import GraphicReversi.GraphicController;
import Model.Errors;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class playReversiStageController {
    public ImageView backReversiStage;
    public TextField component;
    public static Player player2;

    public void playReversi(ActionEvent actionEvent) throws IOException {
        String player2Name = component.getText();
        player2 = Player.findPlayer(player2Name);
        if (player2 != null) {
            Player.setComponentPlayer(player2);
            GraphicController.startGame();
        } else {
            Errors.THIS_USER_DOES_NOT_EXIST.showMessage();
        }
    }

    public void backReversiStage(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("platoForPlayer.fxml"));
        Scene scene = new Scene(root, 508, 743);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage back = (Stage) backReversiStage.getScene().getWindow();
        back.close();
    }
}
