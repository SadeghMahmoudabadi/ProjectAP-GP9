package Model;

import Controller.Controller;
import View.ViewDotsAndBox;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player[] players;
    private int turn;
    private Scanner scanner;
    private boolean isLineDrawn;        //check if line is drawn
    private boolean isGameEnd;
    Random generator = new Random();

    {
        players = new Player[2];
        players[0] = new Player("Player1");
        players[1] = new Player("Player2");
        isLineDrawn = false;
        isGameEnd = false;
    }

    public Game() {

    }

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }

    public boolean isLineDrawn() {
        return isLineDrawn;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public void endGame() {
        this.isGameEnd = true;
    }

    public static void startTheGame() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                Dot dot = new Dot(i, j);
                Dot.addDot(dot);
            }
        }
        Dot.setAllSideDots();
        Line.setAvailableLines();
        ViewDotsAndBox.showTable();
    }

    public void setPlayer1(Player player1) {
        this.players[0] = player1;
    }

    public void setPlayer2(Player player2) {
        this.players[1] = player2;
    }

    public void setTurn() {
        /*int win1 = p1.getWins();
        int win2 = p2.getWins();
        if (win1 > win2) {
            turn = 0;
        } else if (win1 < win2) {
            turn = 1;
        } else {*/
        turn = generator.nextInt(2);
        //}
    }

    public void setLineDrawn(boolean bool) {
        isLineDrawn = bool;
    }

    public void changeTurn() {
        if (Controller.getGame().isLineDrawn()) {
            if (turn == 0) {
                turn = 1;
//            ViewDotsAndBox.showWhoIsNext();
            } else {
                turn = 0;
//            ViewDotsAndBox.showWhoIsNext();
            }
            Controller.getGame().setLineDrawn(false);
        }
    }

    public Player whoIsTurn() {
        return players[turn];
    }

    public int getTurn() {
        return turn;
    }

    public void checkTable(Line line) {
        int count = Box.howManyBoxesMade(line);
        if (count == 1) {
            Player player = whoIsTurn();
            player.incrementScore();
            setLineDrawn(false);
        } else if (count == 2) {
            Player player = whoIsTurn();
            player.incrementScore();
            player.incrementScore();
            setLineDrawn(false);
        }
        if (Line.getAvailableLines().size() == 0) {
            endGame();
        }
    }

    public void forfeit() {
        players[turn].setScore(-16);
        players[1 - turn].setScore(32);
    }
}
