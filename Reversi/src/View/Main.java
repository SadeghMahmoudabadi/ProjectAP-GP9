package View;

import Graphic.*;
import Model.Player;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Welcome to the game!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GraphicController.players[0] = new Player(0, View.grid);
        GraphicController.players[1] = new Player(1, View.grid);
        GraphicController.showGameBoard();
    }
}