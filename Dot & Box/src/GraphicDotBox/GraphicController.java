package GraphicDotBox;

import ControllerDotBox.ControllerDotAndBox;
import ModelDotBox.Game;
import ViewDotBox.ViewDotsAndBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class GraphicController implements Initializable {
    public Label player1Name;
    public Label player2Name;
    Game game = ControllerDotAndBox.getGame();
    public static Button[][] button;
    @FXML
    public GridPane gameGrid;
    @FXML
    public Pane gameBoard;
    public static HashMap<String, Line> lines = new HashMap<>();
    public Button forfeit;
    public Button music;
    public static String drawLine;
    public static Boolean isStartDot = true;
    public static String linePosition;
    public static int firstDot;
    public static int secondDot;
    public static int xStart;
    public static int yStart;
    public static int xEnd;
    public static int yEnd;
    public static boolean playSong = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button = new Button[8][8];
        ControllerDotAndBox.run("start dots and boxes game");
        player1Name.setText(game.getPlayer1().getUser());
        player2Name.setText(game.getPlayer2().getUser());
//        String path = ("Dot & Box/src/Graphic/Music.mp3");
//        Media media = new Media(new File(path).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        music.setStyle(
//                "-fx-background-radius: 100px; " +
//                        "-fx-border-radius: 100px; " +
//                        "-fx-background-color: #419121; " +
//                        "-fx-border-color: #000000;");
//        music.setOnMouseClicked(event -> {
//            playSong = !playSong;
//            mediaPlayer.setVolume(0.5);
//            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//            if (playSong) {
//                music.setStyle(
//                        "-fx-background-radius: 100px; " +
//                                "-fx-border-radius: 100px; " +
//                                "-fx-background-color: #000000; " +
//                                "-fx-border-color: #000000;");
//                mediaPlayer.play();
//            } else {
//                music.setStyle(
//                        "-fx-background-radius: 100px; " +
//                                "-fx-border-radius: 100px; " +
//                                "-fx-background-color: #419121; " +
//                                "-fx-border-color: #000000;");
//                mediaPlayer.pause();
//            }
//        });
        forfeit.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getDialogPane().getButtonTypes().add(cancel);
            alert.setTitle("Forfeit");
            alert.setHeaderText("You have forfeited badbakht-e-zalil");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                game.forfeit();
                Stage stage = (Stage) forfeit.getScene().getWindow();
                stage.close();
                ViewDotsAndBox.showTable();
            }
        });

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

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                button[i][j] = new Button();
                button[i][j].setStyle("-fx-background-radius: 15; " +
                        "-fx-min-width: 15; " +
                        "-fx-min-height: 15; " +
                        "-fx-max-width: 15; " +
                        "-fx-max-height: 15;" +
                        "-fx-background-color: #000000;");

                button[i][j].setAlignment(Pos.TOP_LEFT);
                gameBoard.getChildren().add(button[i][j]);
                button[i][j].setLayoutX(182 + j * 92.7);
                button[i][j].setLayoutY(170 + i * 92.7);
                int finalI = i;
                int finalJ = j;
                button[i][j].setOnMouseClicked(event -> {
                    String path = ("Plato/src/Photos/Music Button.mp3");
                    Media media = new Media(new File(path).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    boolean bool = true;
                    if (isStartDot) {
                        for (int x = 0; x < 8; x++) {
                            for (int y = 0; y < 8; y++) {
                                button[x][y].setStyle("-fx-background-radius: 15; " +
                                        "-fx-min-width: 15; " +
                                        "-fx-min-height: 15; " +
                                        "-fx-max-width: 15; " +
                                        "-fx-max-height: 15;" +
                                        "-fx-background-color: #000000;");
                            }
                        }
                        xStart = finalI + 1;
                        yStart = finalJ + 1;
                        button[finalI][finalJ].setStyle("-fx-background-radius: 15; " +
                                "-fx-min-width: 15; " +
                                "-fx-min-height: 15; " +
                                "-fx-max-width: 15; " +
                                "-fx-max-height: 15;" +
                                "-fx-background-color: Yellow;");
                        isStartDot = false;
                    } else {
                        xEnd = finalI + 1;
                        yEnd = finalJ + 1;
                        button[finalI][finalJ].setStyle("-fx-background-radius: 15; " +
                                "-fx-min-width: 15; " +
                                "-fx-min-height: 15; " +
                                "-fx-max-width: 15; " +
                                "-fx-max-height: 15;" +
                                "-fx-background-color: Yellow;");
                        if (xStart == xEnd) {
                            if (yStart > yEnd) {
                                int temp = yStart;
                                yStart = yEnd;
                                yEnd = temp;
                            }
                        } else if (yStart == yEnd) {
                            if (xStart > xEnd) {
                                int temp = xStart;
                                xStart = xEnd;
                                xEnd = temp;
                            }
                        } else {
                            //Error
                            System.out.println("you can’t draw a line between these two");
                            bool = false;
                        }
                        if (bool) {
                            drawLine = String.format("draw line between (%d,%d) and (%d,%d)", xStart, yStart, xEnd, yEnd);
                            linePosition = String.format("%d%d%d%d", xStart, yStart, xEnd, yEnd);
                            if (ControllerDotAndBox.run(drawLine)) {
                                if (game.getTurn() == 0) {
                                    lines.get(linePosition).setStroke(Color.RED);
                                } else {
                                    lines.get(linePosition).setStroke(Color.BLUE);
                                }
                                game.changeTurn();
                                ViewDotsAndBox.showTable();
                            }
                        }
                        isStartDot = true;
                    }
                });
            }
        }
    }

    public static void startGame() throws IOException {
        Parent root = FXMLLoader.load(GraphicController.class.getResource("dotsAndBoxesFX.fxml"));
        Scene scene = new Scene(root, 1000, 1000);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
