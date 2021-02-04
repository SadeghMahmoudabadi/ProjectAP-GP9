package Graphic;

import Model.Player;
import Network.Client;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;

public class ProfileController {

    public ImageView photo1;
    public ImageView photo3;
    public ImageView photo2;
    public ImageView photo4;

    public void photo1(MouseEvent mouseEvent) {
        Image image = photo1.getImage();
        String[] input = {"player", "edit", "profile", "1"};
        if (Client.requestToServer(input)) {
            GraphicPlatoPlayer.staticProfilePic.setImage(image);
            Stage stage = (Stage) photo4.getScene().getWindow();
            stage.close();
        }
    }

    public void photo2(MouseEvent mouseEvent) {
        Image image = photo2.getImage();
        String[] input = {"player", "edit", "profile", "3"};
        if (Client.requestToServer(input)) {
            GraphicPlatoPlayer.staticProfilePic.setImage(image);
            Stage stage = (Stage) photo4.getScene().getWindow();
            stage.close();
        }
    }

    public void photo3(MouseEvent mouseEvent) {
        Image image = photo3.getImage();
        String[] input = {"player", "edit", "profile", "2"};
        if (Client.requestToServer(input)) {
            GraphicPlatoPlayer.staticProfilePic.setImage(image);
            Stage stage = (Stage) photo4.getScene().getWindow();
            stage.close();
        }
    }

    public void photo4(MouseEvent mouseEvent) {
        Image image = photo4.getImage();
        String[] input = {"player", "edit", "profile", "4"};
        if (Client.requestToServer(input)) {
            GraphicPlatoPlayer.staticProfilePic.setImage(image);
            Stage stage = (Stage) photo4.getScene().getWindow();
            stage.close();
        }
    }
}
