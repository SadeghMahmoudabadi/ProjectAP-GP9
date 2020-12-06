package Model;

import java.util.*;

@SuppressWarnings("ALL")
public class Dot {
    private int xPosition;
    private int yPosition;
    private ArrayList<Dot> sideDots;
    private int sideDotsNum;
    private static HashMap<Integer, Dot> dots = new HashMap<>();
    private static ArrayList<Dot> availableDots = new ArrayList<>();

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
}