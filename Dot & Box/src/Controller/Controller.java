package Controller;


import Model.Dot;
import Model.Game;
import Model.Line;
import Model.Player;
import View.View;

import java.util.*;

public class Controller {
    private static Game game;
    private static View view;

    public Controller() {
    }

    public static void run(String command) {
        if (command.equalsIgnoreCase("start dot and boxes game")) {
            Game.startTheGame();
        } else if (command.startsWith("draw line between")) {

            //method Boolean for if draw line method is done.
        } else if (command.equalsIgnoreCase("end of my turn")) {

        } else if (command.equalsIgnoreCase("show available lines")) {
            for (Line availableLine : Line.getAvailableLines()) {
                System.out.println(availableLine);
            }
        } else if (command.equalsIgnoreCase("show table")) {

        } else if (command.equalsIgnoreCase("who is next?")) {

        } else if (command.equalsIgnoreCase("show result")) {

        } else if (command.equalsIgnoreCase("show score")) {

        }
    }
}
