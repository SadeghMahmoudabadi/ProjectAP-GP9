package ModelDotBox;

import ControllerDotBox.ControllerDotAndBox;
import ViewDotBox.ViewDotsAndBox;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private PlayerDotBox[] playerDotBoxes;
    private int turn;
    private Scanner scanner;
    private boolean isLineDrawn;        //check if line is drawn
    private boolean isGameEnd;
    Random generator = new Random();

    {
        playerDotBoxes = new PlayerDotBox[2];
        playerDotBoxes[0] = new PlayerDotBox("Player1");
        playerDotBoxes[1] = new PlayerDotBox("Player2");
        isLineDrawn = false;
        isGameEnd = false;
    }

    public Game() {

    }

    public PlayerDotBox getPlayer1() {
        return playerDotBoxes[0];
    }

    public PlayerDotBox getPlayer2() {
        return playerDotBoxes[1];
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

    public void setPlayer1(PlayerDotBox playerDotBox1) {
        this.playerDotBoxes[0] = playerDotBox1;
    }

    public void setPlayer2(PlayerDotBox playerDotBox2) {
        this.playerDotBoxes[1] = playerDotBox2;
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
        if (ControllerDotAndBox.getGame().isLineDrawn()) {
            if (turn == 0) {
                turn = 1;
//            ViewDotsAndBox.showWhoIsNext();
            } else {
                turn = 0;
//            ViewDotsAndBox.showWhoIsNext();
            }
            ControllerDotAndBox.getGame().setLineDrawn(false);
        }
    }

    public PlayerDotBox whoIsTurn() {
        return playerDotBoxes[turn];
    }

    public int getTurn() {
        return turn;
    }

    public void checkTable(Line line) {
        int count = Box.howManyBoxesMade(line);
        if (count == 1) {
            PlayerDotBox playerDotBox = whoIsTurn();
            playerDotBox.incrementScore();
            setLineDrawn(false);
        } else if (count == 2) {
            PlayerDotBox playerDotBox = whoIsTurn();
            playerDotBox.incrementScore();
            playerDotBox.incrementScore();
            setLineDrawn(false);
        }
        if (Line.getAvailableLines().size() == 0) {
            endGame();
        }
    }

    public void forfeit() {
        playerDotBoxes[turn].setScore(-16);
        playerDotBoxes[1 - turn].setScore(32);
    }
}
