package Controller;


import Model.Dot;
import Model.Game;
import Model.Line;
import Model.Player;
import View.View;

import java.security.AllPermission;
import java.util.*;


public class Controller {
    private static Game game = new Game();
    private static View view = new View(game);


    public Controller() {
    }

    public static void run(String command) {
        if (command.equalsIgnoreCase("start dot and boxes game")) {
            Game.startTheGame();
        } else if (command.startsWith("draw line between")) {
            int xStart, yStart, xFinish, yFinish;

            //  Line.drawLine(xStart, yStart, xFinish, yFinish);
            game.setLineDrawn(true);
        } else if (command.equalsIgnoreCase("end of my turn")) {
            if (game.isLineDrawn()) {

                game.setLineDrawn(false);
            } else {
                View.showErrors(1);
            }

        } else if (command.equalsIgnoreCase("show available lines")) {
            for (Line availableLine : Line.getAvailableLines()) {
                System.out.println(availableLine);
            }
        } else if (command.equalsIgnoreCase("show table")) {

        } else if (command.equalsIgnoreCase("who is next?")) {

        } else if (command.equalsIgnoreCase("show result")) {

        } else if (command.equalsIgnoreCase("show score")) {
            view.showScore();
        }

    }
}
