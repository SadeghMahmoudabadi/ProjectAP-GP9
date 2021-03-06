package Graphic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class GraphicView {
    private static Button testButton;
    @FXML
    public Button login;
    @FXML
    public ImageView registerBack;
    @FXML
    public JFXTextField loginUser;
    @FXML
    public JFXTextField loginPass;
    @FXML
    public ImageView loginBack;
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

    public static void greetClient() {
        System.out.println("Welcome to this application; choose one from below menu:");
    }

    public static void showAuthorizationMenu() {
        System.out.println("" +
                "- Register\n" +
                "- Login\n" +
                "- exit"
        );
    }

    public static void getName() {
        System.out.print("Enter Name: ");
    }

    public static void getLastName() {
        System.out.print("Enter Last Name: ");
    }

    public static void getEmail() {
        System.out.print("Enter Email: ");
    }

    public static void getPhoneNumber() {
        System.out.print("Enter Phone Number: ");
    }

    public static void getUsername() {
        System.out.print("Enter Username: ");
    }

    public static void getPassword() {
        System.out.print("Enter Password: ");
    }


    /*public static void showProfile(User currentUser) {
        System.out.println(currentUser);

    }*/

    public static void showError(String message) {
        System.out.println(message);
    }

    public static void Play() {
        System.out.println("" +
                "- Reversi\n" +
                "- Dot_and_Box\n" +
                "- Back\n" +
                " - exit"
        );
    }

    public static void showEditMenu() {
        System.out.println("" +
                "- Name\n" +
                "- LastName\n" +
                "- Email\n" +
                "- username\n" +
                "- password\n" +
                "- phoneNumber\n" +
                "- Back\n" +
                "- exit");
    }

    public static void showFieldEdition(String field) {
        System.out.println("Your new " + field + " is : ");
    }

    public void login(MouseEvent mouseEvent) {
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
        StackPane parentContainer = (StackPane)registerBack.getScene().getRoot();
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