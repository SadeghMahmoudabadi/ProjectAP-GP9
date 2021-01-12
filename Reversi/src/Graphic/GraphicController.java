package Graphic;

import Controller.Controller;
import Model.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GraphicController {
    public static Button[][] coordinates;
    public static Player[] players = new Player[2];

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
                gameGridPane.add(coordinates[i][j], j, i);
                int finalJ = j;
                int finalI = i;
                coordinates[i][j].setOnMouseClicked(event -> {
                    String input = String.format("place disk on (%d,%d)", finalI + 1, finalJ + 1);
                    if (Controller.run(input)) {

                    }

                });
            }
        }

        coordinates[3][3].setText("⚫");
        coordinates[3][3].setTextFill(Color.WHITE);
        coordinates[3][4].setText("⚫");
        coordinates[3][4].setTextFill(Color.BLACK);
        coordinates[4][3].setText("⚫");
        coordinates[4][3].setTextFill(Color.BLACK);
        coordinates[4][4].setText("⚫");
        coordinates[4][4].setTextFill(Color.WHITE);
        Pane gamePane = new Pane();
        gameGridPane.setLayoutX(70);
        gameGridPane.setLayoutY(70);
        gamePane.getChildren().add(gameGridPane);
        Button submit = new Button("Submit");
        submit.setLayoutX(560);
        submit.setLayoutY(650);
        submit.setPrefHeight(40);
        submit.setPrefWidth(70);
        Button undo = new Button("Undo");
        undo.setLayoutX(420);
        undo.setLayoutY(650);
        undo.setPrefHeight(40);
        undo.setPrefWidth(70);
        gamePane.getChildren().add(undo);
        gamePane.getChildren().add(submit);
        CheckBox music = new CheckBox("🔊");
        music.setLayoutX(70);
        music.setLayoutY(650);
        music.setPrefHeight(40);
        music.setPrefWidth(40);
        gamePane.getChildren().add(music);
        Font font2 = new Font(20);
        music.setFont(font2);
        Scene gameScene = new Scene(gamePane, 700, 700);
        Stage gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("Reversi Game");
        gameStage.show();
    }

}
