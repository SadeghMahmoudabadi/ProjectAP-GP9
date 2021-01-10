package Graphic;

import Controller.Controller;
import Model.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GraphicController {
    public static Button[][] coordinates;
    public static Player[] players = new Player[2];
    public GridPane gameGrid;

    public static void showGameBoard() {
        GridPane gameGridPane = new GridPane();
        //  gameGridPane.setGridLinesVisible(true);
        coordinates = new Button[8][8];
        Font font = Font.font(30);
        Controller.run("start reversi game");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                coordinates[i][j] = new Button();
                coordinates[i][j].setPrefHeight(70);
                coordinates[i][j].setPrefWidth(70);
                coordinates[i][j].setFont(font);
                coordinates[i][j].setStyle("-fx-background-color: #2e7227 ; -fx-border-color: Black");
                //coordinates[i][j].setStyle("-fx-border-color: Black");
                gameGridPane.add(coordinates[i][j], j, i);
                int finalJ = j;
                int finalI = i;
                coordinates[i][j].setOnMouseClicked(event -> {
                    String input = String.format("place disk on (%d,%d)", finalI + 1, finalJ + 1);
                    if (Controller.run(input)) {

                        //change color

                    }

                });
            }
        }

        coordinates[3][3].setText("⚪");
        coordinates[3][4].setText("⚫");
        coordinates[4][3].setText("⚫");
        coordinates[4][4].setText("⚪");
//        coordinates[5][6].setStyle("-fx-background-color: GREEN");
//        coordinates[7][7].setStyle("-fx-background-color: BLUE");
        Scene gameScene = new Scene(gameGridPane, 600, 600);
//        gameScene.setFill(Paint.valueOf("Black"));
        Stage gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("Reversi Game");
        gameStage.show();
    }

}
