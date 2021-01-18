package Graphic;

import Controller.Controller;
import Model.Player;
import Model.Tools;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphicPlatoPlayer implements Initializable {
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
    public AnchorPane friendsPage;
    public TextField searchBox;
    public JFXButton deleteAcc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticBioLabel = bioLabel;
        staticUsernameLabel = username;
        username.setText(Player.getCurrentPlayer().getUsername());
        coinsNum.setText(Integer.toString(Player.getCurrentPlayer().getCoin()));
        winsNum.setText(Integer.toString(Player.getCurrentPlayer().getWins()));
        friendsNum.setText(Integer.toString(Player.getCurrentPlayer().getFriends().size()));
        dotAndBoxLevel.setText(("Level: " + Player.getCurrentPlayer().getDotAndBoxLevel()));
        dotAndBoxWins.setText(("Wins: " + Player.getCurrentPlayer().getDotAndBoxWins()));
        dotAndBoxPlayedNum.setText(("Played: " + Player.getCurrentPlayer().getDotAndBoxPlayedNum()));
        reversiLevel.setText(("Level: " + Player.getCurrentPlayer().getReversiLevel()));
        reversiWins.setText(("Wins: " + Player.getCurrentPlayer().getReversiWins()));
        reversiPlayedNum.setText(("Played: " + Player.getCurrentPlayer().getReversiPlayedNum()));
        bioLabel.setText(Player.getCurrentPlayer().getBio());
        Stage stage = new Stage();
        TableView<FriendsData> table = new TableView<>();
        ObservableList<FriendsData> data = FXCollections.observableArrayList();
        TableColumn IDCol = new TableColumn("ID");
        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("friendName"));
        TableColumn requestCol = new TableColumn("Friend Request");
        requestCol.setCellValueFactory(new PropertyValueFactory<>("sendFriendRequest"));
        TableColumn acceptCol = new TableColumn("Accept");
        acceptCol.setCellValueFactory(new PropertyValueFactory<>("acceptRequest"));
        TableColumn declineCol = new TableColumn("Decline");
        declineCol.setCellValueFactory(new PropertyValueFactory<>("declineRequest"));
        TableColumn removeCol = new TableColumn("Remove Friend");
        removeCol.setCellValueFactory(new PropertyValueFactory<>("removeFriend"));
        for (Player player : Player.getPlayers()) {
            Button sendFriendRequest = new Button("Friend Request");
            Button accept = new Button("Accept");
            Button decline = new Button("Decline");
            Button remove = new Button("Remove");
            remove.setDisable(true);
            remove.setOnMouseClicked(event -> {
                String[] input = {"remove", Integer.toString(player.getUserID())};
                try {
                    if (Controller.playerMenu(Player.getCurrentPlayer().getUserID(), input)) {
                        friendsNum.setText(Integer.toString(Player.getCurrentPlayer().getFriends().size()));
                        remove.setDisable(true);
                        sendFriendRequest.setDisable(false);
                        sendFriendRequest.setText("Friend Request");
                        accept.setDisable(true);
                        decline.setDisable(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            sendFriendRequest.setOnMouseClicked(event -> {
                String[] input = {"add", "friend", Integer.toString(player.getUserID())};
                System.out.println("are are, are are");
                try {
                    if (Controller.playerMenu(Player.getCurrentPlayer().getUserID(), input)) {
                        sendFriendRequest.setText("Requested!");
                        sendFriendRequest.setDisable(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            if (player.getFriendRequests().contains(Player.getCurrentPlayer().getUserID())
                    || Player.getCurrentPlayer().getFriendRequests().contains(player.getUserID())) {
                sendFriendRequest.setText("Requested!");
                sendFriendRequest.setDisable(true);
            } else if (player.getFriends().contains(Player.getCurrentPlayer().getUserID())) {
                sendFriendRequest.setText("Friend!");
                sendFriendRequest.setDisable(true);
                remove.setDisable(false);
            }
            accept.setTextFill(Color.WHITE);
            accept.setStyle("-fx-background-color: Green");
            accept.setDisable(true);
            accept.setOnMouseClicked(event -> {
                String[] input = {"accept", Integer.toString(player.getUserID())};
                try {
                    if (Controller.playerMenu(Player.getCurrentPlayer().getUserID(), input)) {
                        System.out.println("Accept!");
                        sendFriendRequest.setText("Friend!");
                        friendsNum.setText(Integer.toString(Player.getCurrentPlayer().getFriends().size()));
                        remove.setDisable(false);
                        accept.setDisable(true);
                        decline.setDisable(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            decline.setTextFill(Color.WHITE);
            decline.setStyle("-fx-background-color: Red");
            decline.setDisable(true);
            decline.setOnMouseClicked(event -> {
                String[] input = {"decline", Integer.toString(player.getUserID())};
                try {
                    if (Controller.playerMenu(Player.getCurrentPlayer().getUserID(), input)) {
                        System.out.println("Decline!");
                        accept.setDisable(true);
                        decline.setDisable(true);
                        sendFriendRequest.setDisable(false);
                        sendFriendRequest.setText("Friend Request");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            if (Player.getCurrentPlayer().getFriendRequests().contains(player.getUserID())) {
                accept.setDisable(false);
                decline.setDisable(false);
            }
            if (Player.getCurrentPlayer() != player) {
                data.add(new FriendsData(player.getUsername(), player.getUserID(), sendFriendRequest, accept, decline, remove));
            }
        }
        table.setItems(data);
        table.getSelectionModel().

                setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().

                addAll(IDCol, nameCol, requestCol, acceptCol, declineCol, removeCol);
        table.setPrefWidth(506);
        table.setMinHeight(647);
        table.setLayoutY(50);
        Scene scene = new Scene(table, 395, 625);
        stage.setTitle("Table View Example");
        stage.setScene(scene);
        friendsPage.getChildren().

                add(table);

        FilteredList<FriendsData> filteredData = new FilteredList<>(data, b -> true);
        searchBox.textProperty().

                addListener((observable, oldValue, newValue) ->

                {
                    filteredData.setPredicate(friendsData -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (friendsData.getFriendName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (String.valueOf(friendsData.getID()).indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                });
        SortedList<FriendsData> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().

                bind(table.comparatorProperty());
        table.setItems(sortedData);
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
        Stage stage = (Stage) playDotsGame.getScene().getWindow();
        stage.close();
    }

    public void playReversiGame(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playReversiStage.fxml"));
        Stage playDotsStage = new Stage();
        playDotsStage.setScene(new Scene(root, 385, 464));
        playDotsStage.show();
        Stage stage = (Stage) playReversiGame.getScene().getWindow();
        stage.close();
    }

    public void deleteAccount(MouseEvent mouseEvent) throws IOException {
        String[] input = {"delete", "account"};
        if (Controller.playerMenu(Player.getCurrentPlayer().getUserID(), input)) {
            Stage accStage = (Stage) deleteAcc.getScene().getWindow();
            accStage.close();
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("loginFX.fxml"));
            loginStage.setScene(new Scene(root, 747, 616));
            loginStage.show();
        }
    }
}
