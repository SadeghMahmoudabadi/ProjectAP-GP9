package ModelDotBox;

import ControllerDotBox.ControllerDotAndBox;
import GraphicDotBox.GraphicController;
import Model.Player;
import ViewDotBox.ViewDotsAndBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {
    private PlayerDotBox[] playerDotBoxes;
    private int turn;
    private boolean isLineDrawn;        //check if line is drawn
    private boolean isGameEnd;
    Random generator = new Random();

    {
        playerDotBoxes = new PlayerDotBox[2];
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

    public static void startTheGame() {
        Line.availableLines = new ArrayList<>();
        Line.drawnLines = new ArrayList<>();
        Line.lines = new ArrayList<>();
        Dot.availableDots = new ArrayList<>();
        Dot.dots = new HashMap<>();
        ControllerDotAndBox.setPlayers(Player.getCurrentPlayer(), Player.getComponentPlayer());
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
            endGame("end");
        }
    }

    public void forfeit() {
        playerDotBoxes[turn].setScore(-16);
        playerDotBoxes[1 - turn].setScore(32);
        endGame("forfeit");
    }

    public void endGame(String in) {
        String end = null;
        Player winner = null;
        Player loser = null;
        System.out.println("Game over!");
        playerDotBoxes[0].getMainPlayer().incrementDotAndBoxPlayedNum();
        playerDotBoxes[1].getMainPlayer().incrementDotAndBoxPlayedNum();
        if (in.equalsIgnoreCase("end")) {
            if (playerDotBoxes[0].getScore() > playerDotBoxes[1].getScore()) {
                end = String.format("Winner: %s", playerDotBoxes[0].getUser());
                winner = playerDotBoxes[0].getMainPlayer();
                loser = playerDotBoxes[1].getMainPlayer();
            } else if (playerDotBoxes[1].getScore() > playerDotBoxes[0].getScore()) {
                end = String.format("Winner: %s", playerDotBoxes[1].getUser());
                winner = playerDotBoxes[1].getMainPlayer();
                loser = playerDotBoxes[0].getMainPlayer();
            } else {
                end = String.format("Draw!");
                playerDotBoxes[0].getMainPlayer().addMoney(10);
                playerDotBoxes[1].getMainPlayer().addMoney(10);
                return;
            }
            winner.incrementDotAndBoxWins();
            winner.addMoney(20);
            loser.addMoney(5);
        } else if (in.equalsIgnoreCase("forfeit")) {
            if (playerDotBoxes[0].getScore() > playerDotBoxes[1].getScore()) {
                end = String.format("Winner: %s", playerDotBoxes[0].getUser());
                winner = playerDotBoxes[0].getMainPlayer();
            } else if (playerDotBoxes[1].getScore() > playerDotBoxes[0].getScore()) {
                end = String.format("Winner: %s", playerDotBoxes[1].getUser());
                winner = playerDotBoxes[1].getMainPlayer();
            }
            winner.incrementDotAndBoxWins();
            winner.addMoney(15);
        }
        GraphicController.endGame(end);
    }
}
