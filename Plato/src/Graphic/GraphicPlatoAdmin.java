package Graphic;

import Controller.Controller;
import Model.Admin;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphicPlatoAdmin {
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

    public void addEvent(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Create Events.fxml"));
        Scene scene = new Scene(root, 600, 543);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage event = (Stage) addEvent.getScene().getWindow();
        event.close();
    }

    public void editEvent(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin Edit Event.fxml"));
        Scene scene = new Scene(root, 423, 415);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage edit = (Stage) editEvent.getScene().getWindow();
        edit.close();
    }

    public void addAdmin(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) throws IOException {
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
    }

    public void showSuggest(ActionEvent actionEvent) {
    }
}
