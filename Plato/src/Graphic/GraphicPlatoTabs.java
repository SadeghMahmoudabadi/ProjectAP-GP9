package Graphic;

import Controller.Controller;
import Model.Player;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphicPlatoTabs implements Initializable {
    public TabPane platoTabs;
    public Label username;
    public Label coinsNum;
    public JFXButton editUsername;
    public Label dotAndBoxLevel;
    public Label reversiPlayedNum;
    public Label dotAndBoxWins;
    public Label reversiLevel;
    public Label reversiWins;
    public Label bioLabel;
    public Label friendsNum;
    public ImageView editBioLabel;
    public Label winsNum;
    public Label platoAge;
    public Label dotAndBoxPlayedNum;
    public Button logout;
    public static Label staticBioLabel;
    public static Label staticUsernameLabel;
    public JFXButton playDotsGame;
    public JFXButton playReversiGame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticBioLabel = bioLabel;
        staticUsernameLabel = username;
        username.setText(Player.getCurrentPlayer().getUsername());
        coinsNum.setText(Integer.toString(Player.getCurrentPlayer().getCoin()));
        winsNum.setText(Integer.toString(Player.getCurrentPlayer().getWins()));
        friendsNum.setText(Integer.toString(Player.getCurrentPlayer().getFriends().size()));
        dotAndBoxLevel.setText(("Level: " + Integer.toString(Player.getCurrentPlayer().getDotAndBoxLevel())));
        dotAndBoxWins.setText(("Wins: " + Integer.toString(Player.getCurrentPlayer().getDotAndBoxWins())));
        dotAndBoxPlayedNum.setText(("Played: " + Integer.toString(Player.getCurrentPlayer().getDotAndBoxPlayedNum())));
        reversiLevel.setText(("Level: " + Integer.toString(Player.getCurrentPlayer().getReversiLevel())));
        reversiWins.setText(("Wins: " + Integer.toString(Player.getCurrentPlayer().getReversiWins())));
        reversiPlayedNum.setText(("Played: " + Integer.toString(Player.getCurrentPlayer().getReversiPlayedNum())));
        bioLabel.setText(Player.getCurrentPlayer().getBio());
    }

    public void editUsername(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("usernameFX.fxml"));
        Stage username = new Stage();
        username.setScene(new Scene(root, 293, 183));
        username.show();
    }

    public void editBio(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bioFX.fxml"));
        Stage bio = new Stage();
        bio.setScene(new Scene(root, 293, 183));
        bio.show();
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        String[] input = {"logout"};
        if (Controller.userMenu(Player.getCurrentPlayer().getUserID(), input)) {
            Parent root = FXMLLoader.load(getClass().getResource("loginFX.fxml"));
            Stage stage = (Stage) logout.getScene().getWindow();
            StackPane parentContainer = (StackPane) logout.getScene().getRoot();
            parentContainer.getChildren().add(root);
            parentContainer.getChildren().remove(platoTabs);
            stage.setWidth(747);
            stage.setHeight(616);
        }
    }

    public void playDotsGame(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playDotsStage.fxml"));
        Stage playDotsStage = new Stage();
        playDotsStage.setScene(new Scene(root, 385, 464));
        playDotsStage.show();
        Stage stage = (Stage)playDotsGame.getScene().getWindow();
        stage.close();
    }

    public void playReversiGame(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playReversiStage.fxml"));
        Stage playDotsStage = new Stage();
        playDotsStage.setScene(new Scene(root, 385, 464));
        playDotsStage.show();
        Stage stage = (Stage)playReversiGame.getScene().getWindow();
        stage.close();
    }

}
