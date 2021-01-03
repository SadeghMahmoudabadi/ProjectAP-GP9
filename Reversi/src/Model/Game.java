package Model;

import View.View;

public class Game {
    Grid grid;
    static Player[] players;
    static int turn = 0;
    static boolean isGameOver = false;
    //static boolean endTurn;

    public Game(Player[] players, Grid grid) {
        this.grid = new Grid();
        this.players = players;
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
        /*if (canIEndTurn()) {*/
        turn = (turn + 1) % 2;
            /*setEndTurn(false);
        } else {
            View.showErrors(1);
        }*/
    }

    public static Player whoIsTurn() {
        return players[turn];
    }
}
