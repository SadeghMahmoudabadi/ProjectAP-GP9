package GraphicReversi;

import Model.Player;
import ControllerReversi.ControllerReversi;
import ModelReversi.PlayerReversi;
import ViewReversi.ViewReversi;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphicController implements Initializable {
    public static Button[][] coordinates;
    public static PlayerReversi[] playerReversies = new PlayerReversi[2];
    public Pane gamePane;

    public static void startGame() throws IOException {
        GraphicController.playerReversies[0] = new PlayerReversi(Player.getCurrentPlayer().getUsername(), 0, ViewReversi.grid, Player.getCurrentPlayer());
        GraphicController.playerReversies[1] = new PlayerReversi(Player.getComponentPlayer().getUsername(), 1, ViewReversi.grid, Player.getComponentPlayer());
        Parent root = FXMLLoader.load(GraphicController.class.getResource("reversiFX.fxml"));
        Scene scene = new Scene(root, 1000, 1000);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GridPane gameGridPane = new GridPane();
        //  gameGridPane.setGridLinesVisible(true);
        coordinates = new Button[8][8];
        Font font = Font.font(30);
        ControllerReversi.run("start reversi game");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                coordinates[i][j] = new Button();
                coordinates[i][j].setPrefHeight(95);
                coordinates[i][j].setPrefWidth(95);
                coordinates[i][j].setFont(font);
                coordinates[i][j].setStyle("-fx-background-color: #2e7227 ; -fx-border-color: Black");
                gameGridPane.add(coordinates[i][j], j, i);
                int finalJ = j;
                int finalI = i;
                coordinates[i][j].setOnMouseClicked(event -> {
                    String input = String.format("place disk on (%d,%d)", finalI + 1, finalJ + 1);
                    if (ControllerReversi.run(input)) {

                    }

                });
            }
        }

        coordinates[3][3].setText(String.valueOf('\u2B24'));
        coordinates[3][3].setTextFill(Color.WHITE);
        coordinates[3][4].setText(String.valueOf('\u2B24'));
        coordinates[3][4].setTextFill(Color.BLACK);
        coordinates[4][3].setText(String.valueOf('\u2B24'));
        coordinates[4][3].setTextFill(Color.BLACK);
        coordinates[4][4].setText(String.valueOf('\u2B24'));
        coordinates[4][4].setTextFill(Color.WHITE);
        gameGridPane.setLayoutX(120);
        gameGridPane.setLayoutY(130);
        gamePane.getChildren().add(gameGridPane);
    }

}

