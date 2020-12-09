package Model;

import java.util.*;

public class Game {

    private Player p1;
    private Player p2;
    private int turn;
    private int color;
    private Grid grid;
    private Scanner scanner;
    Random generator = new Random();

    public static int[] countScore = new int[2];


    public Game() {

    }

    public void changeColor(int color) {

    }

    public void changeTurn() {
        if (turn == 0) {
            turn = 1;
        } else
            turn = 0;
    }

    public Player whoIsTurn() {
        if (turn == 1) {
            return p1;
        } else
            return p2;
    }

    public void setTurn() {
        int win1 = p1.getWins();
        int win2 = p2.getWins();
        if (win1 > win2) {
            turn = 0;
        } else if (win1 < win2) {
            turn = 1;
        } else {
            turn = generator.nextInt(2);
        }

    }

    public void checkGrid() {

    }

    public static void startTheGame() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                Coordinate c = new Coordinate(i, j);
                c.addCoordinate(i, j, c);
                if ((i == 4 && j == 4) || (i == 5 && j == 5)) {
                    System.out.print("\u26AA    ");
                } else if ((i == 4 && j == 5) || (i == 5 && j == 4)) {
                    System.out.print("\u26AB    ");
                } else
                    System.out.print("\u22C5     ");

            }
            System.out.println();
            System.out.println();
        }

        countScore[0] = 2;
        countScore[1] = 2;

    }

    public boolean placeDisk(int diskColor, int x, int y) {
        if (isValid(diskColor, x, y, true)) {
            System.out.println("put successful");
            return true;
        } else {
            System.out.print("coordinate(" + x + "," + y + ") is invalid\n ");
            return false;
        }
    }

    public boolean isValid(int color, int x, int y, boolean edit) {
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        if (Coordinate.getCoordinateByPosition(x, y).getColor() != -1) {
            System.out.println("block is full");
            return false;
        }
        boolean valid = false;
        int primeColor = PrimeColor(color);
        for (int i = 0; i < 8; i++) {
            boolean change = false;
            int currentX = x + direction[i][0];
            int currentY = y + direction[i][1];
            while (Coordinate.getCoordinateByPosition(currentX, currentY).getColor() == primeColor &&
                    currentX > 0 && currentY > 0 && currentX < 9 && currentY < 9) {
                currentX += direction[i][0];
                currentY += direction[i][1];
                change = true;
            }
            if (Coordinate.getCoordinateByPosition(currentX, currentY).getColor() == color && change) {
                valid = true;
                if (edit)
                    updateMap(color, x, y, direction[i][0], direction[i][1]);
            }
        }
        if (edit && valid)
            countScore[color]++;
        return valid;
    }

    private void updateMap(int color, int x, int y, int signX, int signY) {
        int primeColor = PrimeColor(color);
        do {
            Coordinate.getCoordinateByPosition(x, y).setColor(color);
            x += signX;
            y += signY;
            countScore[color]++;
            countScore[primeColor]--;
        } while (Coordinate.getCoordinateByPosition(x, y).getColor() == primeColor);
        countScore[primeColor]++;
        countScore[color]--;
    }

    private int PrimeColor(int color) {
        if (color == 1)
            return 0;
        else
            return 1;
    }


    public static int[] getCountScore() {
        return countScore;
    }

    public static void setCountScore(int[] countScore) {
        Game.countScore = countScore;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }


}