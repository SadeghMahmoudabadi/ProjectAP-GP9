package GraphicReversi;

import ModelReversi.PlayerReversi;
import ViewReversi.ViewReversi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Welcome to the game!");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GraphicController.playerReversis[0] = new PlayerReversi(0, ViewReversi.grid);
        GraphicController.playerReversis[1] = new PlayerReversi(1, ViewReversi.grid);
        Parent root = FXMLLoader.load(getClass().getResource("Grid.fxml"));
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }
}