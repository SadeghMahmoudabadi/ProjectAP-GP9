package Graphic;

import Model.Database;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class bioController implements Initializable {

    @FXML
    public TextField bioText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void okBioText(ActionEvent actionEvent) {
        GraphicPlatoTabs.staticBioLabel.setText(bioText.getText());
        Player.getCurrentPlayer().setBio(bioText.getText());
        Stage bioStage = (Stage) bioText.getScene().getWindow();
        bioStage.close();
        Database.updateFiles();
    }
}
