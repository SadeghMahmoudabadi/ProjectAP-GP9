package View;

import Controller.Controller;
import Model.*;

import java.util.*;

import static Model.Game.countScore;


public class View {
    private static Scanner scanner = new Scanner(System.in);
    private Game game;
    Player player1 = game.getP1();
    Player player2 = game.getP2();

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
        if (player1.getScore() > player2.getScore()) {
            System.out.println("Winner: " + player1.getUser());
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("Winner: " + player2.getUser());
        } else {
            System.out.println("Draw!");
        }

    }

    public void showGrid() {
        System.out.println("white: " + Game.countScore[1] + " || black: " + Game.countScore[0] );
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                System.out.format("%s", Coordinate.getCoordinateByPosition(i, j).toString());
            }
            System.out.println("\n");
        }
    }

    public void showScore() {
        player1.setScore(countScore[0]) ;
        player2.setScore(countScore[1]);
        System.out.println("player1: " + Game.countScore[0] + " || player2: " + Game.countScore[1] );


    }


    }

