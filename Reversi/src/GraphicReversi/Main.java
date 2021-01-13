package GraphicReversi;

import Model.Player;
import View.ViewReversi;
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
        GraphicController.players[0] = new Player(0, ViewReversi.grid);
        GraphicController.players[1] = new Player(1, ViewReversi.grid);
        Parent root = FXMLLoader.load(getClass().getResource("Grid.fxml"));
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }
}