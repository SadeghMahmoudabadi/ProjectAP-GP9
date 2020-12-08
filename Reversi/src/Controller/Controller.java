package Controller ;
import java.security.AllPermission;
import java.util.*;
import Model.Coordinate;
import Model.Game;
import View.*;
public class Controller {
    private static Game game = new Game();
    private static View view = new View(game);


    public Controller() {
    }
    public static void run(String command) {
        if (command.equalsIgnoreCase("start Reversi game")) {
            Game.startTheGame();
        } else if (command.startsWith("Place disk on")) {
            int x , y;
            String[] input = command.split("\\s");
            String strdinate= input[3];
            String[] coordinateRegex = strdinate.split("");
            String xStarChar = coordinateRegex[1];
            x = Integer.parseInt(xStarChar);
            String yStartChar = coordinateRegex[3];
            y= Integer.parseInt(yStartChar);

        } else if (command.equalsIgnoreCase("end of my turn")) {

        } else if (command.equalsIgnoreCase("show available coordinates")) {

        } else if (command.equalsIgnoreCase("show grid")) {

        } else if (command.equalsIgnoreCase("who is next?")) {

        } else if (command.equalsIgnoreCase("show result")) {

        } else if (command.equalsIgnoreCase("show score")) {

        } else if (command.equalsIgnoreCase("show disks")) {

        }

    }
}
