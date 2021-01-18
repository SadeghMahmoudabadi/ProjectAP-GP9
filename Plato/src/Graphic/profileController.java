package Graphic;

import Model.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;

public class profileController {

    public ImageView photo1;
    public ImageView photo3;
    public ImageView photo2;
    public ImageView photo4;

    public void photo1(MouseEvent mouseEvent) {
        Image image = photo1.getImage();
        Player.getCurrentPlayer().setProfile(1);
        GraphicPlatoPlayer.staticProfilePic.setImage(image);
        Stage stage = (Stage) photo1.getScene().getWindow();
        stage.close();
    }

    public void photo2(MouseEvent mouseEvent) {
        Image image = photo2.getImage();
        Player.getCurrentPlayer().setProfile(3);
        GraphicPlatoPlayer.staticProfilePic.setImage(image);
        Stage stage = (Stage) photo2.getScene().getWindow();
        stage.close();
    }

    public void photo3(MouseEvent mouseEvent) {
        Image image = photo3.getImage();
        Player.getCurrentPlayer().setProfile(2);
        GraphicPlatoPlayer.staticProfilePic.setImage(image);
        Stage stage = (Stage) photo3.getScene().getWindow();
        stage.close();
    }

    public void photo4(MouseEvent mouseEvent) {
        Image image = photo4.getImage();
        Player.getCurrentPlayer().setProfile(4);
        GraphicPlatoPlayer.staticProfilePic.setImage(image);
        Stage stage = (Stage) photo4.getScene().getWindow();
        stage.close();
    }
}
