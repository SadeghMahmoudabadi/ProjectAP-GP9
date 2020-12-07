package Model;

import java.util.*;

@SuppressWarnings("ALL")
public class Line {
    private String direction;
    private Dot startDot;
    private Dot finishDot;
    private int xStart;
    private int yStart;
    private int xFinish;
    private int yFinish;
    private static ArrayList<Line> lines = new ArrayList<>();
    private static ArrayList<Line> availableLines = new ArrayList<>();

    public Line(Dot startDot, Dot finishDot) {
        this.startDot = startDot;
        this.finishDot = finishDot;
        this.xStart = startDot.getXPosition();
        this.yStart = startDot.getYPosition();
        this.xFinish = finishDot.getXPosition();
        this.yFinish = finishDot.getYPosition();
        this.setDirection();
    }

    public Dot getStartDot() {
        return startDot;
    }

    public Dot getFinishDot() {
        return finishDot;
    }

    public int getxStart() {
        return xStart;
    }

    public int getyStart() {

        return yStart;
    }

    public int getxFinish() {
        return xFinish;
    }

    public int getyFinish() {
        return yFinish;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection() {
        if (this.xStart == this.xFinish) {
            this.direction = "Vertical";
        } else {
            this.direction = "Horizontal";
        }
    }

    public static void addLine(Line line) {
        lines.add(line);
    }

    public static void setAvailableLines() {
        for (int xPosition = 1; xPosition < 9; xPosition++) {
            for (int yPosition = 1; yPosition < 9; yPosition++) {
                Dot dot = Dot.getDotByPosition(xPosition, yPosition);
                for (Dot sideDot : dot.getSideDots()) {
                    Line line = new Line(dot, sideDot);
                    if (!isLineAvailable(line)) {
                        availableLines.add(line);
                    }
                }
            }
        }
    }

    public static void removeAvailabe(Line line) {
        if (isLineAvailable(line)) {
            availableLines.remove(line);
        }
    }

    public static boolean isLineAvailable(Line line) {
        for (Line availableLine : availableLines) {
            Dot dot1 = availableLine.getStartDot();
            Dot dot2 = availableLine.getFinishDot();
            if (line.getStartDot().equals(dot1)) {
                if (line.getFinishDot().equals(dot2)) {
                    return true;
                }
            } else if (line.getStartDot().equals(dot2)) {
                if (line.getFinishDot().equals(dot1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void drawLine(int xStart, int yStart, int xFinish, int yFinish) {
        Dot dot1 = Dot.getDotByPosition(xStart, yStart);
        Dot dot2 = Dot.getDotByPosition(xFinish, yFinish);
        Line line = new Line(dot1, dot2);
        lines.add(line);
        removeAvailabe(line);
    }

    public static Line getLineByDots(Dot dot1, Dot dot2) {
        for (Line line : lines) {
            if (line.getxStart() == dot1.getXPosition() && line.getyStart() == dot1.getYPosition()) {
                if (line.getxFinish() == dot2.getXPosition() && line.getyFinish() == dot2.getYPosition()) {
                    return line;
                }
            } else if (line.getxStart() == dot2.getXPosition() && line.getyStart() == dot2.getYPosition()) {
                if (line.getxStart() == dot1.getXPosition() && line.getyStart() == dot1.getYPosition()) {
                    return line;
                }
            }
        }
        return null;
    }

    public static ArrayList<Line> getAvailableLines() {
        return availableLines;
    }

    public static boolean isConnected(Dot dot1, Dot dot2) {
        for (Line line : lines) {
            if (line.getStartDot().equals(dot1)) {
                if (line.getFinishDot().equals(dot2)) {
                    return true;
                }
            } else if (line.getStartDot().equals(dot2)) {
                if (line.getFinishDot().equals(dot1)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d) and (%d,%d)", xStart, yStart, xFinish, yFinish);
    }
}
