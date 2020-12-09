package Model;

import java.util.*;

public class Coordinate {

    //1 -> white 0->black
    private int color;
    private int xPosition;
    private int yPosition;
    private static HashMap<Integer, Coordinate> coordinates = new HashMap<>();


    public Coordinate(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }


    public int getColor() {
        return color;
    }


    public void setColor(int color) {
        this.color = color;
    }


    public int getxPosition() {
        return xPosition;
    }


    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }


    public int getyPosition() {
        return yPosition;
    }


    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }


    public static void addCoordinate(int x, int y, Coordinate c) {
        int xy = x * 10 + y;
        coordinates.put(xy, c);
    }

    public static Coordinate getCoordinateByPosition(int x, int y) {
        int position = x * 10 + y;
        Coordinate coordinate = coordinates.get(position);
        return coordinate;
    }

    @Override
    public String toString() {
        if (color == 0)
            return ('\u26AB') + "    ";
        else if (color == 1)
            return ('\u26AA') + "    ";
        else
            return ('\u22C5') + "     ";
    }

}

