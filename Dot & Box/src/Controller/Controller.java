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
            String sDot = input[3];
            String fDot = input[5];
            String[] sDot1 = sDot.split(".");// sdot1 =[(] [x1] [,] [y1] [)]
            String[] fDot1 = fDot.split(".");
            String xStart1 = sDot1[1];//x1 = 5
            xStart = Integer.parseInt(xStart1);
            String yStart1 = sDot1[3];
            yStart = Integer.parseInt(yStart1);
            String xFinish1 = fDot1[1];
            xFinish = Integer.parseInt(xFinish1);
            String yFinish1 = fDot1[3];
            yFinish = Integer.parseInt(yFinish1);


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
