package ModelReversi;

import GraphicReversi.GraphicController;
import Model.Player;
import ViewReversi.ViewReversi;

import static java.lang.System.exit;

public class Game {
    public static boolean isGameOver;
    public Grid grid;
    static PlayerReversi[] playerReversies;
    static int turn;

    public Game(PlayerReversi[] playerReversies, Grid grid) {
        this.grid = grid;
        Game.playerReversies = playerReversies;
    }

    /*public static boolean canIEndTurn() {
        return endTurn;
    }

    public static void setEndTurn(boolean bool) {
        endTurn = bool;
    }*/

    public static void changeTurn() {
        if (playerReversies[1 - turn].hasTurn()) {
            turn = (turn + 1) % 2;
        } else if (!playerReversies[turn].hasTurn()) {
            endGame("end");
        }
    }

    public static void startGame() {
        playerReversies = GraphicController.playerReversies;
        turn = 0;
        ViewReversi.grid = new Grid();
        ViewReversi.setIsGameStarted(true);
        ViewReversi.showGrid();
    }

    public static void endGame(String in) {
        String end = null;
        isGameOver = true;
        Player winner = null;
        Player loser = null;
        System.out.println("Game over!");
        playerReversies[0].getMainPlayer().incrementReversiPlayedNum();
        playerReversies[1].getMainPlayer().incrementReversiPlayedNum();
        if (in.equalsIgnoreCase("end")) {
            if (ViewReversi.getGrid().diskCount[0] > ViewReversi.getGrid().diskCount[1]) {
                end = String.format("Winner: %s", playerReversies[0].getUsername());
                winner = playerReversies[0].getMainPlayer();
                loser = playerReversies[1].getMainPlayer();
            } else if (ViewReversi.getGrid().diskCount[1] > ViewReversi.getGrid().diskCount[0]) {
                end = String.format("Winner: %s", playerReversies[1].getUsername());
                winner = playerReversies[1].getMainPlayer();
                loser = playerReversies[0].getMainPlayer();
            } else {
                end = String.format("Draw!");
                playerReversies[0].getMainPlayer().addMoney(10);
                playerReversies[1].getMainPlayer().addMoney(10);
            }
            winner.incrementReversiWins();
            winner.addMoney(20);
            loser.addMoney(5);
        } else if (in.equalsIgnoreCase("forfeit")) {
            if (ViewReversi.getGrid().diskCount[0] > ViewReversi.getGrid().diskCount[1]) {
                end = String.format("Winner: %s", playerReversies[0].getUsername());
                winner = playerReversies[0].getMainPlayer();
            } else if (ViewReversi.getGrid().diskCount[1] > ViewReversi.getGrid().diskCount[0]) {
                end = String.format("Winner: %s", playerReversies[1].getUsername());
                winner = playerReversies[1].getMainPlayer();
            }
            winner.incrementReversiWins();
            winner.addMoney(15);
        }
        GraphicController.endGame(end);
    }

    public static PlayerReversi whoIsTurn() {
        return playerReversies[turn];
    }

    public static void setIsGameOver(boolean b) {
        isGameOver = b;
    }
}
