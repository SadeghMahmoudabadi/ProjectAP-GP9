package Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class playReversiStageController {
    public ImageView backReversiStage;

    public void playReversi(ActionEvent actionEvent) {
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
