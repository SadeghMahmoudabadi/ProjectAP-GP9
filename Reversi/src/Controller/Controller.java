package Controller;

import Model.*;
import View.View;

public class Controller {

    public static void run(String command) {
        if (command.equalsIgnoreCase("start Reversi game")) {
            Game game = new Game(View.getPlayers(), View.getGrid());
            View.players[0] = new Player(0, View.grid);
            View.players[1] = new Player(1, View.grid);
            View.setIsGameStarted(true);
            View.showGrid();
        } else if (command.startsWith("place disk on ")) {
            int x, y;
            String position = command.split(" ")[3];
            if (position.length() > 5) {
                View.showErrors(2);
            } else {
                x = Integer.parseInt(position.split("")[1]);
                y = Integer.parseInt(position.split("")[3]);
                Game.whoIsTurn().placeDisk(String.format("%d %d", x, y));
            }
        } /*else if (command.equalsIgnoreCase("end of my turn")) {
            Game.changeTurn();
        } /*else if (command.equalsIgnoreCase("show available coordinates")) {
            View.showAvailableCoordinates();
        }*/ else if (command.equalsIgnoreCase("show grid")) {
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
    }
}
