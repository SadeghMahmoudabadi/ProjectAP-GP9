package Graphic;

import Graphic.CreatesEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class dotsEventController implements Initializable {
    public Button openJoinDotBox;
    public Label eventPrize;

    public void openJoinDotBox(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventPrize.setText(CreatesEvent.eventCoin);
    }
}
