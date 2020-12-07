package View;

import Controller.Controller;
import Model.*;

import java.util.*;

public class View {
    private static Scanner scanner;
    private Game game;

    {
        scanner = new Scanner(System.in);
    }

    public View(Game game) {
        this.game = game;
    }

    public static void getCommand() {
        String command;
        while (true/*Line.getAvailableLines().size() != 0*/) {
            command = scanner.nextLine();
            Controller.run(command);
        }
    }

    public void showAvailableDots() {

    }

    public void showScore() {
        Player player1 = game.getP1();
        Player player2 = game.getP2();
        System.out.println(player1.getUser() + ": " + player1.getScore());
        System.out.println(player2.getUser() + ": " + player2.getScore());
    }

    public static void showErrors(int errorID) {
        if (errorID == 1) {
            System.out.println("in your turn you should draw a line");
        } else if (errorID == 2) {
            System.out.println("coordinates must be inside the table");
        } else {
            System.out.println("you can’t draw a line between these two");
        }
    }
}
