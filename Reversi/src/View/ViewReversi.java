package View;

import Controller.Controller;
import Graphic.GraphicController;
import Model.Game;
import Model.Grid;

import java.util.Scanner;

public class ViewReversi {

    public static Grid grid = new Grid();
    static boolean isGameStarted = false;
    private static Scanner scanner = new Scanner(System.in);

    public static Grid getGrid() {
        return grid;
    }

    public static void showErrors(int errorID) {
        if (errorID == 1) {
            System.out.println("in your turn you should place the disk");
        } else if (errorID == 2) {
            System.out.println("Coordinates must be inside the table");
        } else {
            System.out.println("You cannot place the disk on this Coordinates");
        }
    }

    public static void getCommand() {
        int i = 0;
        while (true) {
            if (!isGameStarted) {
                Controller.run(scanner.nextLine());
            } else {
                int color = Game.whoIsTurn().getColor();
                if (color == 0 && Game.whoIsTurn().hasTurn()) {
                    System.out.println("white turn!");
                } else if (color == 1 && Game.whoIsTurn().hasTurn()) {
                    System.out.println("black turn!");
                } else if ((GraphicController.players[0].hasTurn()) || (GraphicController.players[1].hasTurn())) {
                    Game.changeTurn();
                    //View.showErrors(1);
                } else {
                    Game.setIsGameOver(true);
                }
                if (GraphicController.players[i % 2].hasTurn()) {
                    Controller.run(scanner.nextLine());
                } else if (GraphicController.players[(i + 1) % 2].hasTurn()) {
                    i = (i + 1) % 2;
                    Controller.run(scanner.nextLine());
                } else {
                    showScore();
                    showResult();
                    break;
                }
                i = (i + 1) % 2;
            }
        }
    }

    public static void setIsGameStarted(boolean isGameStarted) {
        ViewReversi.isGameStarted = isGameStarted;
    }


    /*public static void showAvailableCoordinates() {

    }*/

    public static void showGrid() {
        showScore();
        System.out.println("      1  \u2009  2  \u2009\u2009  3  \u2009\u2009  4  \u2009\u2009  5  \u2009\u2009  6  \u2009\u2009  7  \u2009\u2009  8");
        System.out.println();
        for (int i = 1; i < 9; i++) {
            System.out.printf(" %d  ", i);
            for (int j = 1; j < 9; j++) {
                if (grid.coordinates[i][j].isLastDisk()) {
                    System.out.printf(" >%s< ", grid.coordinates[i][j].toString());
                } else {
                    System.out.printf("  %s  ", grid.coordinates[i][j].toString());
                }
            }
            System.out.println("\n");
        }
        grid.resetGrid();
    }

    public static void showDisks() {
        showGrid();
    }

    public static void showWhoIsNext() {
        System.out.println(Game.whoIsTurn().getUsername());
    }

    public static void showResult() {
        int[] diskCount = grid.diskCount;
        if (diskCount[0] > diskCount[1]) {
            System.out.println("White wins!");
        } else if (diskCount[1] > diskCount[0]) {
            System.out.println("Black wins!");
        } else {
            System.out.println("Draw!!!");
        }
    }

    public static void showScore() {
        System.out.println("white: " + grid.diskCount[0] + " || black: " + grid.diskCount[1]);
    }


}

