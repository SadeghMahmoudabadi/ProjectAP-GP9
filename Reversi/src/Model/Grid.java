package Model;

import java.util.*;

public class Grid {
    private static ArrayList<Coordinate> availabeCoordinatesForBlack = new ArrayList<>();
    private static ArrayList<Coordinate> availabeCoordinatesForWhite = new ArrayList<>();

    public Grid() {
    }

    //from UML
    public void addAvailabeCoordinatesForBlack(Coordinate c) {
        availabeCoordinatesForBlack.add(c);
    }

    //from UML
    public void addAvailabeCoordinatesForWhite(Coordinate c) {
        availabeCoordinatesForWhite.add(c);
    }

    //from UML
    public void removeAvailabeCoordinatesForBlack(Coordinate c) {
        availabeCoordinatesForBlack.remove(c);
    }

    //from UML
    public void removeAvailabeCoordinatesForWhite(Coordinate c) {
        availabeCoordinatesForWhite.remove(c);

    }
}
