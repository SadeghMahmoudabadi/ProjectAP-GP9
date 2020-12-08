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
            String[] input = command.split("\\s");
            String startDotCmd = input[3];
            String finishDotCmd = input[5];
            String[] startDotRegex = startDotCmd.split("");
            String[] finishDotRegex = finishDotCmd.split("");
            String xStarChar = startDotRegex[1];
            xStart = Integer.parseInt(xStarChar);
            String yStartChar = startDotRegex[3];
            yStart = Integer.parseInt(yStartChar);
            String xFinishChar = finishDotRegex[1];
            xFinish = Integer.parseInt(xFinishChar);
            String yFinishChar = finishDotRegex[3];
            yFinish = Integer.parseInt(yFinishChar);
            Dot startDot = Dot.getDotByPosition(xStart, yStart);
            Dot finishDot = Dot.getDotByPosition(xFinish, yStart);
            Line line = Line.drawLine(xStart, yStart, xFinish, yFinish);
            game.setLineDrawn(true);
            game.checkTable(line);
        } else if (command.equalsIgnoreCase("end of my turn")) {
            if (game.isLineDrawn()) {
                game.changeTurn();
                game.setLineDrawn(false);
            } else {
                View.showErrors(1);
            }

        } else if (command.equalsIgnoreCase("show available lines")) {
            view.showAvailableLines();
        } else if (command.equalsIgnoreCase("show table")) {
            view.showTable();
        } else if (command.equalsIgnoreCase("who is next?")) {

        } else if (command.equalsIgnoreCase("show result")) {

        } else if (command.equalsIgnoreCase("show score")) {
            view.showScore();
        }

    }
}
