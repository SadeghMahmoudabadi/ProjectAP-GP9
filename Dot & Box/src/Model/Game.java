package Model;

import java.util.*;

public class Game {
    private Player p1;
    private Player p2;
    private int turn;
    private Scanner scanner;
    Random generator = new Random();

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
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

    public void checkTable(Line line) {
        int count = Box.howManyBoxesMade(line);
        if (count == 0) {
            changeTurn();
        } else if (count == 1) {
            Player player = whoIsTurn();
            player.incrementScore();
        } else if (count == 2) {
            Player player = whoIsTurn();
            player.incrementScore();
            player.incrementScore();
        }
    }
}
