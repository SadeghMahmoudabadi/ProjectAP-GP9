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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphicController implements Initializable {

    public static Button[][] button;
    @FXML
    public GridPane gameGrid;
    @FXML
    public Pane gameBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button = new Button[8][8];
        // Boolean isStartDot = true;
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
