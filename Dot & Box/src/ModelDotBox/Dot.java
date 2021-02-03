package ModelDotBox;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class Dot {
    private int xPosition;
    private int yPosition;
    private ArrayList<Dot> sideDots;
    private int sideDotsNum;
    public static HashMap<Integer, Dot> dots;
    public static ArrayList<Dot> availableDots;

    {
        sideDots = new ArrayList<>();
    }

    public Dot(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getXPosition() {
        return this.xPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }

    public ArrayList<Dot> getSideDots() {
        return this.sideDots;
    }

    public int getSideDotsNum() {
        return this.sideDotsNum;
    }

    public static HashMap<Integer, Dot> getDots() {
        return dots;
    }

    public static ArrayList<Dot> getAvailableDots() {
        return availableDots;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void addSideDot(Dot sideDot) {
        this.sideDots.add(sideDot);
    }

    public void setSideDotsNum(int sideDotsNum) {
        this.sideDotsNum = sideDotsNum;
    }

    public static void addDot(Dot dot) {
        int x = dot.getXPosition();
        int y = dot.getYPosition();
        dots.put(x * 10 + y, dot);
    }

    public static void addAvailableDot(Dot availableDot) {
        availableDots.add(availableDot);
    }

    public boolean isSideDotAvailable(Dot sideDot) {
        if (this.sideDots.contains(sideDot)) {
            return true;
        } else {
            return false;
        }
    }

    public static void removeAvailableDot(Dot dot) {
        if (availableDots.contains(dot)) {
            availableDots.remove(dot);
        }

    }

    public static boolean isAvailableDot(Dot dot) {
        if (availableDots.contains(dot)) {
            return true;
        } else {
            return false;
        }
    }

    public static Dot getDotByPosition(int x, int y) {
        int position = x * 10 + y;
        return dots.get(position);
    }

    public void addSideDots(Dot dot) {
        this.sideDots.add(dot);
    }

    public void decrementSideDotsNum() {
        this.sideDotsNum--;
    }

    public static void setAllSideDots() {
        for (int xPosition = 1; xPosition < 9; xPosition++) {
            for (int yPosition = 1; yPosition < 9; yPosition++) {
                if (xPosition == 1 && yPosition == 1) {
                    Dot dot = getDotByPosition(xPosition, yPosition);
                    dot.addSideDot(getDotByPosition(xPosition + 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition + 1));
                } else if (xPosition == 8 && yPosition == 1) {
                    Dot dot = getDotByPosition(xPosition, yPosition);
                    dot.addSideDot(getDotByPosition(xPosition - 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition + 1));
                } else if (xPosition == 1 && yPosition == 8) {
                    Dot dot = getDotByPosition(xPosition, yPosition);
                    dot.addSideDot(getDotByPosition(xPosition, yPosition - 1));
                    dot.addSideDot(getDotByPosition(xPosition + 1, yPosition));
                } else if (xPosition == 8 && yPosition == 8) {
                    Dot dot = getDotByPosition(xPosition, yPosition);
                    dot.addSideDot(getDotByPosition(xPosition - 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition - 1));
                } else if ((xPosition > 1 && xPosition < 8) && yPosition == 1) {
                    Dot dot = getDotByPosition(xPosition, yPosition);
                    dot.addSideDot(getDotByPosition(xPosition + 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition - 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition + 1));
                } else if (xPosition == 1 && (yPosition > 1 && yPosition < 8)) {
                    Dot dot = getDotByPosition(xPosition, yPosition);
                    dot.addSideDot(getDotByPosition(xPosition, yPosition - 1));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition + 1));
                    dot.addSideDot(getDotByPosition(xPosition + 1, yPosition));
                } else if ((xPosition > 1 && xPosition < 8) && yPosition == 8) {
                    Dot dot = getDotByPosition(xPosition, yPosition);
                    dot.addSideDot(getDotByPosition(xPosition + 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition - 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition - 1));
                } else if (xPosition == 8 && (yPosition < 8 && yPosition > 1)) {
                    Dot dot = getDotByPosition(xPosition, yPosition);
                    dot.addSideDot(getDotByPosition(xPosition - 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition - 1));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition + 1));
                } else {
                    Dot dot = getDotByPosition(xPosition, yPosition);
                    dot.addSideDot(getDotByPosition(xPosition + 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition - 1, yPosition));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition - 1));
                    dot.addSideDot(getDotByPosition(xPosition, yPosition + 1));
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dot dot = (Dot) obj;
        return xPosition == dot.xPosition &&
                yPosition == dot.yPosition;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", xPosition, yPosition);
    }
}