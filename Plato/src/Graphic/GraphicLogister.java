package Graphic;

import Model.Tools;
import Network.Client;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class GraphicLogister {
    @FXML
    public Button login;
    @FXML
    public ImageView registerBack;
    @FXML
    public JFXTextField loginUser;
    @FXML
    public JFXPasswordField loginPass;
    @FXML
    public JFXTextField registerUser;
    @FXML
    public JFXTextField registerPass;
    @FXML
    public JFXTextField registerFirstname;
    @FXML
    public JFXTextField registerLastname;
    @FXML
    public JFXTextField registerEmail;
    @FXML
    public JFXTextField registerPhoneNumber;
    @FXML
    public JFXButton createAcc;
    @FXML
    public Button register;
    @FXML
    public StackPane parentContainer;
    @FXML
    public GridPane gridRoot;

    public void login(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String username = loginUser.getText();
        String password = loginPass.getText();
        String[] input = {"logister", "login", username, password};
        if (Client.requestToServer(input)) {
            String[] request = {"tools", "isAdmin", username};
            if (Client.requestToServer(request)) {
                Parent root = FXMLLoader.load(getClass().getResource("platoForAdmin.fxml"));
                Stage loginStage = (Stage) login.getScene().getWindow();
                loginStage.close();
                Scene scene = new Scene(root, 377, 578);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("platoForPlayer.fxml"));
                Stage loginStage = (Stage) login.getScene().getWindow();
                loginStage.close();
                Scene scene = new Scene(root, 508, 743);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    public void registerBack(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("loginFX.fxml"));
        Scene scene = registerBack.getScene();
        root.translateYProperty().set(-scene.getHeight());
        StackPane parentContainer = (StackPane) registerBack.getScene().getRoot();
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(gridRoot);
        });
        timeline.play();
    }

    public void register(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String username = registerUser.getText();
        String password = registerPass.getText();
        String firstname = registerFirstname.getText();
        String lastname = registerLastname.getText();
        String email = registerEmail.getText();
        String phoneNumber = registerPhoneNumber.getText();
        String[] input = {"logister", "register", username, password, firstname, lastname, email, phoneNumber};
        if (Client.requestToServer(input)) {
            registerBack(mouseEvent);
        }
    }

    public void createAcc(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("registerFX.fxml"));
        Scene scene = createAcc.getScene();
        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(gridRoot);
        });
        timeline.play();
    }
}