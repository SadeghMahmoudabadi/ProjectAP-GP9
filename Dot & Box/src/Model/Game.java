package Model;
import java.util.*;

public class Game {
    private Player p1;
    private Player p2;
    private int turn;
    private Scanner scanner;
    private boolean isLineDrawn;        //check if line is drawn
    private boolean isGameEnd;
    Random generator = new Random();

    {
        p1 = new Player("Player1");
        p2 = new Player("Player2");
        isLineDrawn = false;
        isGameEnd = false;
    }

    public Game() {

    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
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
                System.out.print("\u26AB    ");
                Dot dot = new Dot(i, j);
                Dot.addDot(dot);
            }
            System.out.println();
            System.out.println();
        }
        Dot.setAllSideDots();
        Line.setAvailableLines();
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
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
        if (turn == 0) {
            turn = 1;
        } else
            turn = 0;
    }

    public Player whoIsTurn() {
        if (turn == 0) {
            return p1;
        } else
            return p2;
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
}
