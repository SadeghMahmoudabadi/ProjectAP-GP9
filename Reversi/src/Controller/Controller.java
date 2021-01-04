package Controller;

import Graphic.GraphicController;
import Model.Game;
import View.*;

public class Controller {

    public static boolean run(String command) {
        if (command.equalsIgnoreCase("start Reversi game")) {
            Game game = new Game(GraphicController.players, View.getGrid());
            View.setIsGameStarted(true);
            View.showGrid();
            return true;
        } else if (command.startsWith("place disk on ")) {
            int x, y;
            String position = command.split(" ")[3];
            if (position.length() > 5) {
                View.showErrors(2);
                return false;
            } else {
                x = Integer.parseInt(position.split("")[1]);
                y = Integer.parseInt(position.split("")[3]);
                return Game.whoIsTurn().placeDisk(String.format("%d %d", x, y));
            }
        } else if (command.equalsIgnoreCase("end of my turn")) {
            Game.changeTurn();
            //   } else if (command.equalsIgnoreCase("show available coordinates")) {
            //   View.showAvailableCoordinates();
        } else if (command.equalsIgnoreCase("show grid")) {
            View.showGrid();
        } else if (command.equalsIgnoreCase("who is next?")) {
            View.showWhoIsNext();
        } else if (command.equalsIgnoreCase("show result")) {
            if (Game.isGameOver()) {
                View.showResult();
            }
        } else if (command.equalsIgnoreCase("show score")) {
            View.showScore();
        } else if (command.equalsIgnoreCase("show disks")) {
            View.showDisks();
        }
        return false;
    }
}
