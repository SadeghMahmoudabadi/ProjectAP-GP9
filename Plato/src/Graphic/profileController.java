package Graphic;

import Model.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class profileController {

    public ImageView photo1;
    public ImageView photo3;
    public ImageView photo2;
    public ImageView photo4;

    public void photo1(MouseEvent mouseEvent) {
        Image image = photo1.getImage();
        Player.getCurrentPlayer().setProfile(image);
        GraphicPlatoPlayer.staticProfilePic.setImage(image);
    }

    public void photo3(MouseEvent mouseEvent) {
        Image image = photo3.getImage();
        Player.getCurrentPlayer().setProfile(image);
        GraphicPlatoPlayer.staticProfilePic.setImage(image);
    }

    public void photo2(MouseEvent mouseEvent) {
        Image image = photo2.getImage();
        Player.getCurrentPlayer().setProfile(image);
        GraphicPlatoPlayer.staticProfilePic.setImage(image);
    }

    public void photo4(MouseEvent mouseEvent) {
        Image image = photo4.getImage();
        Player.getCurrentPlayer().setProfile(image);
        GraphicPlatoPlayer.staticProfilePic.setImage(image);
    }
}
