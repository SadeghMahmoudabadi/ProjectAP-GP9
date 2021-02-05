package Graphic;

import Model.Event;
import Model.Player;
import Network.Client;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class GraphicPlatoPlayer implements Initializable {
    public static ImageView staticProfilePic;
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
    public ImageView profilePic;
    public AnchorPane platoBotMessage;
    public ImageView dotsFav;
    public ImageView reversiFav;
    public TextField editFirstname;
    public TextField editLastname;
    public TextField editEmail;
    public TextField editNumber;
    public Label firstname;
    public Label lastname;
    public Label email;
    public Label phoneNumber;
    public AnchorPane eventsPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        long platoAgeDays = ChronoUnit.DAYS.between(Client.currentPlayer.getRegisterDate(), LocalDate.now());
        platoAge.setText(Long.toString(platoAgeDays) + "d");
        staticBioLabel = bioLabel;
        staticUsernameLabel = username;
        staticProfilePic = profilePic;
        username.setText(Client.currentPlayer.getUsername());
        coinsNum.setText(Integer.toString(Client.currentPlayer.getCoin()));
        winsNum.setText(Integer.toString(Client.currentPlayer.getWins()));
        friendsNum.setText(Integer.toString(Client.currentPlayer.getFriends().size()));
        dotAndBoxLevel.setText(("Level: " + Client.currentPlayer.getDotAndBoxLevel()));
        dotAndBoxWins.setText(("Wins: " + Client.currentPlayer.getDotAndBoxWins()));
        dotAndBoxPlayedNum.setText(("Played: " + Client.currentPlayer.getDotAndBoxPlayedNum()));
        reversiLevel.setText(("Level: " + Client.currentPlayer.getReversiLevel()));
        reversiWins.setText(("Wins: " + Client.currentPlayer.getReversiWins()));
        reversiPlayedNum.setText(("Played: " + Client.currentPlayer.getReversiPlayedNum()));
        bioLabel.setText(Client.currentPlayer.getBio());
        if (Client.currentPlayer.getProfile() != 0) {
            String profilePath = String.format("..\\ProfilePhoto\\%d.png", Client.currentPlayer.getProfile());
            Image image = new Image(getClass().getResourceAsStream(profilePath));
            profilePic.setImage(image);
        }
        TableView<FriendsData> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<FriendsData> data = FXCollections.observableArrayList();
        TableColumn IDCol = new TableColumn("ID");
        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("friendName"));
        TableColumn requestCol = new TableColumn("Friend");
        requestCol.setCellValueFactory(new PropertyValueFactory<>("sendFriendRequest"));
        TableColumn acceptCol = new TableColumn("Accept");
        acceptCol.setCellValueFactory(new PropertyValueFactory<>("acceptRequest"));
        TableColumn declineCol = new TableColumn("Decline");
        declineCol.setCellValueFactory(new PropertyValueFactory<>("declineRequest"));
        TableColumn removeCol = new TableColumn("Remove");
        removeCol.setCellValueFactory(new PropertyValueFactory<>("removeFriend"));
        TableColumn infoCol = new TableColumn("Info");
        infoCol.setCellValueFactory(new PropertyValueFactory<>("personalInfo"));
        for (Player player : Client.getPlayers()) {
            if (player.getUserID() != Client.currentPlayer.getUserID()) {
                Button sendFriendRequest = new Button("Request");
                Button accept = new Button("Accept");
                Button decline = new Button("Decline");
                Button remove = new Button("Remove");
                Button info = new Button("Info");
                info.setOnMouseClicked(event -> {
                    String path = ("Plato/src/Music/Menu Button.mp3");
                    Media media = new Media(new File(path).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    Stage infoStage = new Stage();
                    VBox infoVBox = new VBox();
                    String firstname = String.format("Firstname :    %s", player.getFirstname());
                    String lastname = String.format("Lastname :    %s", player.getLastName());
                    String email = String.format("Email :    %s", player.getEmail());
                    String phoneNumber = String.format("Phone number :    %s", player.getPhoneNumber());
                    String coins = String.format("Coin :    %s", player.getCoin());
                    Label firstnameLabel = new Label(firstname);
                    Label lastnameLabel = new Label(lastname);
                    Label emailLabel = new Label(email);
                    Label phoneNumberLabel = new Label(phoneNumber);
                    Label coinLabel = new Label(coins);
                    firstnameLabel.setFont(Font.font(13));
                    lastnameLabel.setFont(Font.font(13));
                    emailLabel.setFont(Font.font(13));
                    phoneNumberLabel.setFont(Font.font(13));
                    coinLabel.setFont(Font.font(13));
                    infoVBox.getChildren().addAll(firstnameLabel, lastnameLabel, emailLabel, phoneNumberLabel, coinLabel);
                    infoVBox.setAlignment(Pos.CENTER);
                    infoVBox.setSpacing(23);
                    Scene infoScene = new Scene(infoVBox, 350, 350);
                    infoStage.setScene(infoScene);
                    infoStage.show();
                });
                remove.setDisable(true);
                remove.setOnMouseClicked(event -> {
                    String path = ("Plato/src/Music/Menu Button.mp3");
                    Media media = new Media(new File(path).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    String[] input = {"player", "remove", Integer.toString(player.getUserID())};
                    if (Client.requestToServer(input)) {
                        friendsNum.setText(Integer.toString(Client.currentPlayer.getFriends().size()));
                        remove.setDisable(true);
                        sendFriendRequest.setDisable(false);
                        sendFriendRequest.setText("Request");
                        accept.setDisable(true);
                        decline.setDisable(true);
                    }
                });
                sendFriendRequest.setOnMouseClicked(event -> {
                    String path = ("Plato/src/Music/Menu Button.mp3");
                    Media media = new Media(new File(path).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    String[] input = {"player", "add", "friend", Integer.toString(player.getUserID())};
                    if (Client.requestToServer(input)) {
                        sendFriendRequest.setText("Requested!");
                        sendFriendRequest.setDisable(true);
                    }
                });
                if (player.getFriendRequests().contains(Client.currentPlayer.getUserID())
                        || Client.currentPlayer.getFriendRequests().contains(player.getUserID())) {
                    sendFriendRequest.setText("Requested!");
                    sendFriendRequest.setDisable(true);
                } else if (player.getFriends().contains(Client.currentPlayer.getUserID())) {
                    sendFriendRequest.setText("Friend!");
                    sendFriendRequest.setDisable(true);
                    remove.setDisable(false);
                }
                accept.setTextFill(Color.WHITE);
                accept.setStyle("-fx-background-color: Green");
                accept.setDisable(true);
                accept.setOnMouseClicked(event -> {
                    String[] input = {"player", "accept", Integer.toString(player.getUserID())};
                    if (Client.requestToServer(input)) {
                        System.out.println("Accept!");
                        sendFriendRequest.setText("Friend!");
                        friendsNum.setText(Integer.toString(Client.currentPlayer.getFriends().size()));
                        remove.setDisable(false);
                        accept.setDisable(true);
                        decline.setDisable(true);
                    }
                });
                decline.setTextFill(Color.WHITE);
                decline.setStyle("-fx-background-color: Red");
                decline.setDisable(true);
                decline.setOnMouseClicked(event -> {
                    String path = ("Plato/src/Music/Menu Button.mp3");
                    Media media = new Media(new File(path).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    String[] input = {"player", "decline", Integer.toString(player.getUserID())};
                    if (Client.requestToServer(input)) {
                        System.out.println("Decline!");
                        accept.setDisable(true);
                        decline.setDisable(true);
                        sendFriendRequest.setDisable(false);
                        sendFriendRequest.setText("Request");
                    }
                });
                if (Client.currentPlayer.getFriendRequests().contains(player.getUserID())) {
                    accept.setDisable(false);
                    decline.setDisable(false);
                }
                if (Client.currentPlayer != player) {
                    data.add(new FriendsData(player.getUsername(), player.getUserID(), sendFriendRequest, accept, decline, remove, info));
                }
            }
        }
        table.setItems(data);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(IDCol, nameCol, requestCol, acceptCol, declineCol, removeCol, infoCol);
        table.setMinWidth(506);
        table.setMinHeight(647);
        table.setLayoutY(50);
        friendsPage.getChildren().add(table);

        FilteredList<FriendsData> filteredData = new FilteredList<>(data, b -> true);
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        TableView<MessagesData> platoBotMessages = new TableView<>();
        platoBotMessages.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<MessagesData> botData = FXCollections.observableArrayList();
        TableColumn messageColumn = new TableColumn("Message");
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        int i = 0;
        for (int messageID : Client.currentPlayer.getMessages().keySet()) {
            MessagesData messagesData = new MessagesData(messageID, Client.currentPlayer.getMessages().get(messageID), Client.currentPlayer.getUsername());
            botData.add(i++, messagesData);
        }
        platoBotMessages.setItems(botData);
        platoBotMessages.getSelectionModel().setCellSelectionEnabled(true);
        platoBotMessages.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        platoBotMessages.getColumns().addAll(messageColumn);
        platoBotMessages.setPrefWidth(508);
        platoBotMessages.setMinHeight(714);
        platoBotMessage.getChildren().add(platoBotMessages);
        if (Client.currentPlayer.getFavoriteGames().contains("dotsAndBoxes")) {
            dotsFav.setVisible(true);
        }
        if (Client.currentPlayer.getFavoriteGames().contains("reversi")) {
            reversiFav.setVisible(true);
        }
        firstname.setText(Client.currentPlayer.getFirstname());
        lastname.setText(Client.currentPlayer.getLastName());
        email.setText(Client.currentPlayer.getEmail());
        phoneNumber.setText(Client.currentPlayer.getPhoneNumber());
    }

    public void editUsername(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("usernameFX.fxml"));
        Stage username = new Stage();
        username.setScene(new Scene(root, 293, 183));
        username.show();
    }

    public void editBio(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("bioFX.fxml"));
        Stage bio = new Stage();
        bio.setScene(new Scene(root, 293, 183));
        bio.show();
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String[] input = {"user", "logout"};
        if (Client.requestToServer(input)) {
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
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("playDotsStage.fxml"));
        Stage playDotsStage = new Stage();
        playDotsStage.setScene(new Scene(root, 385, 464));
        playDotsStage.show();
        Stage stage = (Stage) playDotsGame.getScene().getWindow();
        stage.close();
    }

    public void playReversiGame(ActionEvent actionEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("playReversiStage.fxml"));
        Stage playDotsStage = new Stage();
        playDotsStage.setScene(new Scene(root, 385, 464));
        playDotsStage.show();
        Stage stage = (Stage) playReversiGame.getScene().getWindow();
        stage.close();
    }

    public void deleteAccount(MouseEvent mouseEvent) throws IOException {
        String path = ("Plato/src/Music/Menu Button.mp3");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        String[] input = {"player", "delete", "account"};
        if (Client.requestToServer(input)) {
            Stage accStage = (Stage) deleteAcc.getScene().getWindow();
            accStage.close();
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("loginFX.fxml"));
            loginStage.setScene(new Scene(root, 747, 616));
            loginStage.show();
        }
    }

    public void addImage(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profilePhotos.fxml"));
        Stage image = new Stage();
        image.setScene(new Scene(root, 600, 564));
        image.show();
    }

    public void addDotsFav(MouseEvent mouseEvent) throws IOException {
        if (Client.currentPlayer.getFavoriteGames().contains("dotsAndBoxes")) {
            String[] input = {"player", "remove", "favorite", "dotsAndBoxes"};
            Client.requestToServer(input);
            dotsFav.setVisible(false);
        } else {
            String[] input = {"player", "add", "favorite", "dotsAndBoxes"};
            Client.requestToServer(input);
            dotsFav.setVisible(true);
        }
    }

    public void addReversiFav(MouseEvent mouseEvent) throws IOException {
        if (Client.currentPlayer.getFavoriteGames().contains("reversi")) {
            String[] input = {"player", "remove", "favorite", "reversi"};
            Client.requestToServer(input);
            reversiFav.setVisible(false);
        } else {
            String[] input = {"player", "add", "favorite", "reversi"};
            Client.requestToServer(input);
            reversiFav.setVisible(true);
        }
    }

    public void editField(MouseEvent mouseEvent) {
        if (!editFirstname.getText().isEmpty()) {
            String[] input = {"user", "edit", "firstname", editFirstname.getText()};
            if (Client.requestToServer(input)) {
                firstname.setText(Client.currentPlayer.getFirstname());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edit");
                alert.setHeaderText("Edit successful");
                alert.setContentText("You edit your firstname");
                alert.show();
            }
        }
        if (!editLastname.getText().isEmpty()) {
            String[] input = {"user", "edit", "lastname", editLastname.getText()};
            if (Client.requestToServer(input)) {
                lastname.setText(Client.currentPlayer.getLastName());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edit");
                alert.setHeaderText("Edit successful");
                alert.setContentText("You edit your lastname");
                alert.show();
            }
        }
        if (!editEmail.getText().isEmpty()) {
            String[] input = {"user", "edit", "email", editEmail.getText()};
            if (Client.requestToServer(input)) {
                email.setText(Client.currentPlayer.getEmail());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edit");
                alert.setHeaderText("Edit successful");
                alert.setContentText("You edit your email");
                alert.show();
            }
        }
        if (!editNumber.getText().isEmpty()) {
            String[] input = {"user", "edit", "phoneNumber", editNumber.getText()};
            Client.requestToServer(input);
            phoneNumber.setText(Client.currentPlayer.getPhoneNumber());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit");
            alert.setHeaderText("Edit successful");
            alert.setContentText("You edit your phone number");
            alert.show();
        }
    }

    public void selectEventTab() {
        VBox eventsVBox = new VBox();
        eventsVBox.setSpacing(23);
        eventsVBox.setLayoutX(13);
        for (Event event : Client.getEvents()) {
            try {
                eventsVBox.getChildren().add(event.getEventScene().getRoot());
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        eventsPane.getChildren().add(eventsVBox);
    }
}
