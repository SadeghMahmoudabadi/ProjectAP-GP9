package Graphic;

import Controller.Controller;
import Model.Admin;
import Model.Player;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphicPlatoAdmin implements Initializable {
    public Button addEvent;
    public Button editEvent;
    public TabPane platoTabs;
    public Button addAdmin;
    public JFXButton logoutAdmin;
    public TextField gameName;
    public TextField playerIDTextField;
    public JFXButton sendSuggest;
    public TextField suggestID;
    public JFXButton okDeleteSuggest;
    public JFXButton showSuggest;
    public Label adminUsername;
    public Pane playersTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminUsername.setText(Admin.getCurrentAdmin().getUsername());
        Stage stage = new Stage();
        TableView<PlayersData> table = new TableView<PlayersData>();
        ObservableList<PlayersData> data = FXCollections.observableArrayList();
        TableColumn IDColumn = new TableColumn("ID");
        IDColumn.setCellValueFactory(new PropertyValueFactory("ID"));
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        int i  = 0;
        for (Player player : Player.getPlayers()) {
            data.add(i++, new PlayersData(player.getUsername(), player.getUserID()));
        }
        ObservableList<String> list = FXCollections.observableArrayList();
        table.setItems(data);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(IDColumn, nameColumn);
        table.setPrefWidth(377);
        table.setMinHeight(539);
        Scene scene = new Scene(table, 395, 625);
        stage.setTitle("Table View Example");
        stage.setScene(scene);
        playersTable.getChildren().add(table);
    }

    public void addEvent(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("Create Events.fxml"));
        Scene scene = new Scene(root, 600, 543);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage event = (Stage) addEvent.getScene().getWindow();
        event.close();
    }

    public void editEvent(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("admin Edit Event.fxml"));
        Scene scene = new Scene(root, 423, 415);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage edit = (Stage) editEvent.getScene().getWindow();
        edit.close();
    }

    public void addAdmin(ActionEvent actionEvent) {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String[] input = {"logout"};
        if (Controller.userMenu(Admin.getCurrentAdmin().getUserID(), input)) {
            Parent root = FXMLLoader.load(getClass().getResource("loginFX.fxml"));
            Stage stage = (Stage) logoutAdmin.getScene().getWindow();
            StackPane parentContainer = (StackPane) logoutAdmin.getScene().getRoot();
            parentContainer.getChildren().add(root);
            parentContainer.getChildren().remove(platoTabs);
            stage.setWidth(747);
            stage.setHeight(616);
        }
    }

    public void sendSuggest(ActionEvent actionEvent) {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void showSuggest(ActionEvent actionEvent) {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
