package View;

import Model.Dot;
import Model.Game;
import Model.Line;
import Model.Player;
import Controller.*;

import java.util.Scanner;

public class ViewDotsAndBox {
    private static Scanner scanner = new Scanner(System.in);
    private static Game game;

    public ViewDotsAndBox(Game game) {
        this.game = game;
    }

    public static void getCommand() {
        String command;
        while (true/*Line.getAvailableLines().size() != 0*/) {     /*****************/
            command = scanner.nextLine();
            Controller.run(command);
        }
    }

    public static void showAvailableLines() {
        for (Line availableLine : Line.getAvailableLines()) {
            System.out.println(availableLine);
        }
    }

    public static void showTable() {
        showScore();
        System.out.println("    1  \u2009  2  \u2009\u2009  3  \u2009\u2009  4  \u2009\u2009 5  \u2009\u2009 6  \u2009\u2009  7  \u2009\u2009  8");
        System.out.println();
        for (int i = 1; i < 9; i++) {
            System.out.printf(" %d  ", i);
            for (int j = 1; j < 9; j++) {
                System.out.print("\u26AB");
                if (Line.isConnected(Dot.getDotByPosition(i, j), Dot.getDotByPosition(i, j + 1)) && j < 8) {
                    System.out.print("----");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println();
            System.out.print("    ");
            for (int j = 1; j < 9; j++) {
                if (Line.isConnected(Dot.getDotByPosition(i, j), Dot.getDotByPosition(i + 1, j)) && i < 8) {
                    System.out.print("\u2009|");
                } else {
                    System.out.print(" \u2009");
                }
                System.out.print("    ");
            }
            System.out.println();
        }
        showWhoIsNext();
    }

    public static void showScore() {
        Player player1 = game.getP1();
        Player player2 = game.getP2();
        System.out.println(player1.getUser() + ": " + player1.getScore());
        System.out.println(player2.getUser() + ": " + player2.getScore());
    }

    public static void showResult() {
        if (game.isGameEnd()) {
            showScore();
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

    public static void showWhoIsNext() {
        System.out.println(game.whoIsTurn().getUser());
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
