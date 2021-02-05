package Graphic;

import Controller.Controller;
import Model.Admin;
import Model.Player;
import Network.Client;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class GraphicPlatoAdmin implements Initializable {
    public Button addEvent;
    public Button editEvent;
    public TabPane platoTabs;
    public Button addAdmin;
    public JFXButton logoutAdmin;
    public ChoiceBox gameName;
    public TextField playerIDTextField;
    public JFXButton sendSuggest;
    public TextField suggestID;
    public JFXButton okDeleteSuggest;
    public JFXButton showSuggest;
    public Label adminUsername;
    public Pane playersTable;
    ObservableList<String> gameChoiceBar = FXCollections.observableArrayList("Dots & Boxes", "Reversi");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameName.setItems(gameChoiceBar);
        adminUsername.setText(Client.currentAdmin.getUsername());
        TableView<PlayersData> table = new TableView<PlayersData>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<PlayersData> data = FXCollections.observableArrayList();
        TableColumn IDColumn = new TableColumn("ID");
        IDColumn.setCellValueFactory(new PropertyValueFactory("ID"));
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        int i  = 0;
        for (Player player : Client.getPlayers()) {
            data.add(i++, new PlayersData(player.getUsername(), player.getUserID()));
        }
        table.setItems(data);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(IDColumn, nameColumn);
        table.setPrefWidth(377);
        table.setMinHeight(548);
        playersTable.getChildren().add(table);
    }

    public void addEvent(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("createEvents.fxml"));
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
        String[] input = {"user", "logout"};
        if (Client.requestToServer(input)) {
            Parent root = FXMLLoader.load(getClass().getResource("loginFX.fxml"));
            Stage stage = (Stage) logoutAdmin.getScene().getWindow();
            StackPane parentContainer = (StackPane) logoutAdmin.getScene().getRoot();
            parentContainer.getChildren().add(root);
            parentContainer.getChildren().remove(platoTabs);
            stage.setWidth(747);
            stage.setHeight(616);
        }
    }

    public void sendSuggest(ActionEvent actionEvent) throws ParseException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String[] input = {"admin", "add", "suggestion", playerIDTextField.getText(), String.valueOf(gameName.getValue())};
        Client.requestToServer(input);
    }

    public void showMessages(ActionEvent actionEvent) {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Stage suggestionStage = new Stage();
        TableView<MessagesData> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<MessagesData> data = FXCollections.observableArrayList();
        TableColumn IDColumn = new TableColumn("ID");
        IDColumn.setCellValueFactory(new PropertyValueFactory("ID"));
        TableColumn playerColumn = new TableColumn("Player");
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        TableColumn messageColumn = new TableColumn("Message");
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        int i = 0;
        for (Player player : Client.getPlayers()) {
            for (int messageID : player.getMessages().keySet()) {
                MessagesData messagesData = new MessagesData(messageID, player.getMessages().get(messageID), player.getUsername());
                data.add(i++, messagesData);
            }
        }
        table.setItems(data);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(IDColumn, playerColumn, messageColumn);
        table.setPrefWidth(377);
        table.setMinHeight(548);
        Scene scene = new Scene(table, 395, 625);
        suggestionStage.setScene(scene);
        suggestionStage.show();
    }

    public void okDeleteSuggest(MouseEvent mouseEvent) throws ParseException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String[] input = {"remove", "suggestion", suggestID.getText()};
        Controller.adminMenu(Admin.getCurrentAdmin().getUserID(), input);
    }
}
