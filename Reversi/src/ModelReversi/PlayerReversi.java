package ModelReversi;

import Model.Player;
import ViewReversi.ShowErrors;
import ViewReversi.ViewReversi;

public class PlayerReversi {
    protected int color;
    protected Grid grid;
    private String username;
    private Player mainPlayer;


    public PlayerReversi(String username, int color, Grid grid, Player mainPlayer) {
        this.username = username;
        this.color = color;
        this.grid = grid;
        this.mainPlayer = mainPlayer;
    }

    public int getColor() {
        return color;
    }

    public String getUsername() {
        return username;
    }

    public boolean placeDisk(String input) {
        int x = Integer.parseInt(String.valueOf(input.charAt(0)));
        int y = Integer.parseInt(String.valueOf(input.charAt(2)));
        try {
            boolean bool = grid.placeDisk(color, x, y);
            if (bool) {
                ViewReversi.showGrid();
                Game.changeTurn();
                ViewReversi.showWhoIsNext();
                return true;
            } else {
                ShowErrors.INVALID_COORDINATES.showMessage();
                ViewReversi.showErrors(3);
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ViewReversi.showErrors(2);
            return false;
        }
    }

    public boolean hasTurn() {
        Disk[][] map = grid.getCoordinates();
        boolean flag = false;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (map[i][j].getColor() == -1 && hasDiskAround(i, j))
                    if (grid.isValid(color, i, j, false)) {
                        map[i][j].setValidLocate(true);
                        flag = true;
                    }
            }
        }
        return flag;
    }

    protected boolean hasDiskAround(int x, int y) {
        Disk[][] map = grid.getCoordinates();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (map[x + i][y + j].getColor() != -1 && map[x + i][y + j].getColor() != color)
                    return true;
            }
        }
        return false;
    }

    public Player getMainPlayer() {
        return mainPlayer;
    }
}
