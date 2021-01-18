import Graphic.CreatesEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class reversiEventContorller implements Initializable {
    public Button joinReversiEvent;
    public Label eventPrize;

    public void openJoinReversi(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventPrize.setText(CreatesEvent.eventCoin);
    }
}
