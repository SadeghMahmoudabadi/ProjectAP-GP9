package Model;
import java.util.*;
public class Coordinate {

    //1 -> white 0->black
    private int color;
    private int xPosition ;
    private int yPosition ;
    private static HashMap<Integer, Coordinate> coordinates = new HashMap<>();

    //constructor
    public Coordinate(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    //added from nut class
    public int getColor() {
        return color;
    }

    //added from nut class
    public void setColor(int color) {
        this.color = color;
    }

    //new
    public int getxPosition() {
        return xPosition;
    }

    //from UML
    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    //new
    public int getyPosition() {
        return yPosition;
    }

    //from UML
    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    // from UML
    public static void addCoordinate(int x , int y, Coordinate c){
        int xy = x*10 + y ;
        coordinates.put(xy,c);
    }

    public static Coordinate getCoordinateByPosition(int x, int y) {
        int position = x*10 + y;
        Coordinate coordinate = coordinates.get(position);
        return coordinate;
    }

    //added from nut class
    //using unicode for dots and nit colors
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

