package Graphic;

import Model.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage loginStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginFX.fxml"));
        loginStage.setScene(new Scene(root, 747, 616));
        loginStage.show();
    }

    public static void main(String[] args) {
        Database.readFiles();
        launch(args);
    }
}
