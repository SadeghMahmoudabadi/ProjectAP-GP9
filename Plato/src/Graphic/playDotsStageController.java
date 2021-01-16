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
import javafx.stage.Stage;

import java.io.IOException;

public class playDotsStageController {

    public TextField component;
    public static Player player2;

    public void playDots(ActionEvent actionEvent) throws IOException {
        String player2Name = component.getText();
        player2 = Player.findPlayer(player2Name);
        if (player2 != null) {
            Player.setComponentPlayer(player2);
            GraphicController.startGame();
        } else {
            //Error
        }
    }


}
