package Controller;


import Model.Dot;
import Model.Game;
import Model.Line;
import Model.Player;
import View.View;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;

public class Controller {
    private static Game game;
    private static View view;

    public Controller() {
    }

    public static void run(String command) {
        if (command.equalsIgnoreCase("start dot and boxes game")) {
            game = new Game();
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    System.out.print("\u26AB    ");
                    Dot dot = new Dot(i, j);
                    Dot.addDot(dot);
                }
                System.out.println();
                System.out.println();
            }
            //add side dots

            //  Player p1 = new Player(p1.getUser() ,p1.getWins());
            //get players from plato

        } else if (command.startsWith("draw line between")) {

            //method Boolean for if draw line method is done.
        } else if (command.equalsIgnoreCase("end of my turn")) {

        } else if (command.equalsIgnoreCase("show available lines")) {

        } else if (command.equalsIgnoreCase("show table")) {

        } else if (command.equalsIgnoreCase("who is next?")) {

        } else if (command.equalsIgnoreCase("show result")) {

        } else if (command.equalsIgnoreCase("show score")) {

        }
    }
}
