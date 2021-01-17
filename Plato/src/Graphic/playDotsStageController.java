package Graphic;

import ControllerDotBox.ControllerDotAndBox;
import GraphicDotBox.GraphicController;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class playDotsStageController {

    public TextField component;
    public static Player player2;
    public ImageView backDotsStage;

    public void playDots(ActionEvent actionEvent) throws IOException {
        String player2Name = component.getText();
        player2 = Player.findPlayer(player2Name);
        if (player2 != null) {
            Player.setComponentPlayer(player2);
            GraphicController.startGame();
        } else {
            //Error   پلیر وجود ندارد
        }
    }


    public void backDotsStage(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("platoForPlayer.fxml"));
        Scene scene = new Scene(root, 508, 743);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage back = (Stage) backDotsStage.getScene().getWindow();
        back.close();
    }
}
