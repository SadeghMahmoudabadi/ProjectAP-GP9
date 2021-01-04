package Model;

import Graphic.GraphicController;

public class Grid {
    public Disk[][] coordinates;
    public int[] diskCount;

    public Grid() {
        coordinates = new Disk[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                coordinates[i][j] = new Disk(-1);
            }
        }
        coordinates[4][4] = new Disk(0);
        coordinates[5][5] = new Disk(0);
        coordinates[4][5] = new Disk(1);
        coordinates[5][4] = new Disk(1);
        diskCount = new int[2];
        diskCount[0] = 2;
        diskCount[1] = 2;
    }

    public boolean placeDisk(int diskColor, int x, int y) {
        if (isValid(diskColor, x, y, true)) {
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    if (coordinates[i][j].isLastDisk()) {
                        coordinates[i][j].setLastDisk(false);
                    }
                }
            }
            coordinates[x][y].setLastDisk(true);
            return true;
        } else {
            return false;
        }
    }

    public boolean isValid(int color, int x, int y, boolean edit) {
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        if (coordinates[x][y].getColor() != -1) {
            return false;
        }
        boolean valid = false;
        int primeColor = PrimeColor(color);
        for (int i = 0; i < 8; i++) {
            boolean change = false;
            int currentX = x + direction[i][0];
            int currentY = y + direction[i][1];
            while (coordinates[currentX][currentY].getColor() == primeColor && currentX > 0 && currentY > 0 && currentX < 9 && currentY < 9) {
                currentX += direction[i][0];
                currentY += direction[i][1];
                change = true;
            }
            if (coordinates[currentX][currentY].getColor() == color && change) {
                valid = true;
                if (edit)
                    updateGrid(color, x, y, direction[i][0], direction[i][1]);
            }
        }
        if (edit && valid)
            diskCount[color]++;
        return valid;
    }

    private void updateGrid(int color, int x, int y, int signX, int signY) {
        int primeColor = PrimeColor(color);
        do {
            coordinates[x][y].setColor(color);
            if (color == 0) {
                GraphicController.coordinates[x - 1][y - 1].setText("⚪");
            } else
                GraphicController.coordinates[x - 1][y - 1].setText("⚫");


            x += signX;
            y += signY;
            diskCount[color]++;
            diskCount[primeColor]--;
        } while (coordinates[x][y].getColor() == primeColor);
        diskCount[primeColor]++;
        diskCount[color]--;
    }

    private int PrimeColor(int color) {
        if (color == 1)
            return 0;
        else
            return 1;
    }

    public void resetGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                coordinates[i][j].setValidLocate(false);
            }
        }
    }

    public Disk[][] getCoordinates() {
        return coordinates;
    }
}
