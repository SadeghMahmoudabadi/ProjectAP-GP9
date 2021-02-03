package ViewDotBox;

import ControllerDotBox.ControllerDotAndBox;
import GraphicDotBox.GraphicController;
import ModelDotBox.Dot;
import ModelDotBox.Game;
import ModelDotBox.Line;
import ModelDotBox.PlayerDotBox;
import javafx.scene.control.Alert;

import java.util.Scanner;

public class ViewDotsAndBox {
    private static Scanner scanner = new Scanner(System.in);

    public ViewDotsAndBox() {

    }

    public static void getCommand() {
        String command;
        while (true/*Line.getAvailableLines().size() != 0*/) {     /*****************/
            command = scanner.nextLine();
            ControllerDotAndBox.run(command);
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
        PlayerDotBox playerDotBox1 = ControllerDotAndBox.getGame().getPlayer1();
        PlayerDotBox playerDotBox2 = ControllerDotAndBox.getGame().getPlayer2();
        System.out.println(playerDotBox1.getUser() + ": " + playerDotBox1.getScore());
        System.out.println(playerDotBox2.getUser() + ": " + playerDotBox2.getScore());
    }

    public static void showResult() {
        if (ControllerDotAndBox.getGame().isGameEnd()) {
            showScore();
            PlayerDotBox playerDotBox1 = ControllerDotAndBox.getGame().getPlayer1();
            PlayerDotBox playerDotBox2 = ControllerDotAndBox.getGame().getPlayer2();
            if (playerDotBox1.getScore() > playerDotBox2.getScore()) {
                System.out.println("Winner: " + playerDotBox1.getUser());
            } else if (playerDotBox2.getScore() > playerDotBox1.getScore()) {
                System.out.println("Winner: " + playerDotBox2.getUser());
            } else {
                System.out.println("Draw!");
            }
        }
    }

    public static void showWhoIsNext() {
        System.out.println(ControllerDotAndBox.getGame().whoIsTurn().getUser());
    }

    public static void showErrors(int errorID) {
        if (errorID == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("in your turn you should draw a line");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you can’t draw a line between these two");
            alert.show();
        }
    }
}
