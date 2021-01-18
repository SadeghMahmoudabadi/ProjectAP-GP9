package ModelReversi;

import ViewReversi.ViewReversi;

import static java.lang.System.exit;

public class Game {
    public static boolean isGameOver;
    Grid grid;
    static PlayerReversi[] playerReversies;
    static int turn = 0;

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

    public static void endGame(String in) {
        isGameOver = true;
        System.out.println("Game over!");
        playerReversies[0].getMainPlayer().incrementReversiPlayedNum();
        playerReversies[1].getMainPlayer().incrementReversiPlayedNum();
        if (in.equalsIgnoreCase("end")) {
            if (ViewReversi.getGrid().diskCount[0] > ViewReversi.getGrid().diskCount[1]) {
                System.out.println("Winner: " + playerReversies[0].getUsername());
                playerReversies[0].getMainPlayer().incrementReversiWins();
            } else if (ViewReversi.getGrid().diskCount[1] > ViewReversi.getGrid().diskCount[0]) {
                System.out.println("Winner: " + playerReversies[1].getUsername());
                playerReversies[1].getMainPlayer().incrementReversiWins();
            } else {
                System.out.println("Draw!");
            }
        } else if (in.equalsIgnoreCase("forfeit")) {
            if (ViewReversi.getGrid().diskCount[0] > ViewReversi.getGrid().diskCount[1]) {
                System.out.println("Winner: " + playerReversies[0].getUsername());
                playerReversies[0].getMainPlayer().incrementReversiWins();
            } else if (ViewReversi.getGrid().diskCount[1] > ViewReversi.getGrid().diskCount[0]) {
                System.out.println("Winner: " + playerReversies[1].getUsername());
                playerReversies[1].getMainPlayer().incrementReversiWins();
            }
        }
    }

    public static PlayerReversi whoIsTurn() {
        return playerReversies[turn];
    }

    public static void setIsGameOver(boolean b) {
        isGameOver = b;
    }
}
