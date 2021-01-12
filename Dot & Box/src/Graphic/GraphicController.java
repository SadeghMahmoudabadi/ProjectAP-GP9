package Graphic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GraphicController implements Initializable {

    public static Button[][] button;
    @FXML
    public GridPane gameGrid;
    @FXML
    public Pane gameBoard;
    public static HashMap<String, Line> lines = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Media
        button = new Button[8][8];
        // Boolean isStartDot = true;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                String position = String.format("%d%d%d%d", j + 1, i + 1, j + 1, i + 2);
                Line line = new Line();
                line.setStartX(198 + i * 93);
                line.setStartY(178 + j * 92.5);
                line.setEndX(274 + i * 93);
                line.setEndY(178 + j * 92.5);
                line.setStrokeWidth(10);
                line.setStroke(Color.TRANSPARENT);
                lines.put(position, line);
                gameBoard.getChildren().add(line);
            }

        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                Line line = new Line();
                String position = String.format("%d%d%d%d", j + 1, i + 1, j + 2, i + 1);
                line.setStartX(190 + i * 92.5);
                line.setStartY(185 + j * 93);
                line.setEndX(190 + i * 92.5);
                line.setEndY(261 + j * 93);
                line.setStrokeWidth(10);
                line.setStroke(Color.TRANSPARENT);
                lines.put(position, line);
                gameBoard.getChildren().add(line);
            }

        }
        lines.get("2535").setStroke(Color.BLUE);
        lines.get("2526").setStroke(Color.RED);


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                button[i][j] = new Button();
                button[i][j].setStyle("" +
                        "-fx-background-radius: 15; " +
                        "-fx-min-width: 15; " +
                        "-fx-min-height: 15; " +
                        "-fx-max-width: 15; " +
                        "-fx-max-height: 15;" +
                        "-fx-background-color: #000000;");

                button[i][j].setAlignment(Pos.TOP_LEFT);
                gameBoard.getChildren().add(button[i][j]);
                button[i][j].setLayoutX(182 + i * 92.7);
                button[i][j].setLayoutY(170 + j * 92.7);
                int finalI = i;
                int finalJ = j;
                button[i][j].setOnMouseClicked(event -> {
                });
            }
        }
    }

}
