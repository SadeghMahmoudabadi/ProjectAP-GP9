package Graphic;

import com.sun.org.omg.CORBA.Initializer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GraphicController implements Initializable {

    public static Button[][] button;
    @FXML
    public GridPane gameGrid;
    @FXML
    public Pane gameBoard;
    public static HashMap<String,Line> redLines = new HashMap<>();
    public static HashMap<String,Line> blueLines = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button = new Button[8][8];
        // Boolean isStartDot = true;
for(int i = 0; i < 7; i++){
    for(int j =0 ; j<8 ; j++){
        Line line1 = new Line();
        line1.setStartX(198 + i*93);
        line1.setStartY(178 + j*92.5);
        line1.setEndX(274 + i*93);
        line1.setEndY(178 + j*92.5);
        line1.setStrokeWidth(10);
        line1.setStroke(Color.RED);
        String position = String.format("%d%d%d%d" , i , j , i , j);
        redLines.put(position , line1);
        line1.setVisible(false);
        gameBoard.getChildren().add(line1);
        Line line2 = new Line();
        line2.setStartX(198 + i*93);
        line2.setStartY(178 + j*92.5);
        line2.setEndX(274 + i*93);
        line2.setEndY(178 + j*92.5);
        line2.setStrokeWidth(10);
        line2.setStroke(Color.BLUE);
        line2.setVisible(false);
        gameBoard.getChildren().add(line2);
    }

}
        for(int i = 0; i < 8; i++){
            for(int j =0 ; j<7 ; j++){
                Line line1 = new Line();
                line1.setStartX(190 + i*92.5);
                line1.setStartY(185 + j*93);
                line1.setEndX(190 + i*92.5);
                line1.setEndY(365 + j*93);
                line1.setStrokeWidth(10);
                line1.setStroke(Color.RED);
                String position = String.format("%d%d%d%d" , i , j , i , j);
                redLines.put(position , line1);
                line1.setVisible(false);
                gameBoard.getChildren().add(line1);

                Line line2 = new Line();
                line2.setStartX(190 + i*92.5);
                line2.setStartY(185 + j*93);
                line2.setEndX(190 + i*92.5);
                line2.setEndY(365 + j*93);
                line2.setStrokeWidth(10);
                line2.setStroke(Color.BLUE);
                line2.setVisible(false);
                gameBoard.getChildren().add(line2);

            }

        }
        redLines.get("3444").setVisible(true);


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
