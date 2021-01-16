package Graphic;

import Controller.Controller;
import Model.Database;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class usernameController {
    public TextField usernameText;

    public void okUsernameText(ActionEvent actionEvent) {
        String[] input = {"edit", "username", usernameText.getText()};
        if (Controller.userMenu(Player.getCurrentPlayer().getUserID(), input)) {
            GraphicPlatoTabs.staticUsernameLabel.setText(usernameText.getText());
            Stage usernameStage = (Stage) usernameText.getScene().getWindow();
            usernameStage.close();
            Database.updateFiles();
        }
    }
}
