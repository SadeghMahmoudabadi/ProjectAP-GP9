package ModelReversi;

public class Game {
    Grid grid;
    static PlayerReversi[] playerReversis;
    static int turn = 0;
    static boolean isGameOver = false;
    //static boolean endTurn;

    public Game(PlayerReversi[] playerReversis, Grid grid) {
        this.grid = new Grid();
        Game.playerReversis = playerReversis;
    }

    /*public static boolean canIEndTurn() {
        return endTurn;
    }

    public static void setEndTurn(boolean bool) {
        endTurn = bool;
    }*/

    public static boolean isGameOver() {
        return isGameOver;
    }

    public static void setIsGameOver(boolean isGameOver) {
        Game.isGameOver = isGameOver;
    }

    public static void changeTurn() {
            turn = (turn + 1) % 2;
    }

    public static PlayerReversi whoIsTurn() {
        return playerReversis[turn];
    }
}
