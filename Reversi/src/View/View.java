package View;

import Controller.Controller;
import Model.*;

import java.util.*;

public class View {
    private static Scanner scanner = new Scanner(System.in);
    private Game game;

    public View(Game game) {
        this.game = game;
    }

    public static void getCommand() {
        String command;
        while (true/*Line.getAvailableLines().size() != 0*/) {     /*****************/
            command = scanner.nextLine();
            Controller.run(command);
        }
    }

    public void showResult() {
        Player player1 = game.getP1();
        Player player2 = game.getP2();
        if (player1.getScore() > player2.getScore()) {
            System.out.println("Winner: " + player1.getUser());
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("Winner: " + player2.getUser());
        } else {
            System.out.println("Draw!");
        }

    }
}
