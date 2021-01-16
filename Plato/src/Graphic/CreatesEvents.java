package Graphic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatesEvents implements Initializable {

    @FXML
    public DatePicker startsDate;
    @FXML
    public DatePicker endsDate;
    @FXML
    public ChoiceBox gameChoiceBox;
    @FXML
    public TextField coinsEvents;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startsDate.getValue();
        endsDate.getValue();
        System.out.println(startsDate);
        System.out.println(endsDate);

    }

}
