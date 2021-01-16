package ModelDotBox;

import ViewDotBox.ViewDotsAndBox;

import java.util.ArrayList;

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
    private static ArrayList<Line> drawnLines = new ArrayList<>();
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

    public static void addDrawnLine(Line line) {
        drawnLines.add(line);
    }

    public static void setAvailableLines() {
        for (int xPosition = 1; xPosition < 9; xPosition++) {
            for (int yPosition = 1; yPosition < 9; yPosition++) {
                Dot dot = Dot.getDotByPosition(xPosition, yPosition);
                for (Dot sideDot : dot.getSideDots()) {
                    Line line = new Line(dot, sideDot);
                    if (!isLineAvailable(line)) {
                        availableLines.add(line);
                        lines.add(line);
                    }
                }
            }
        }
    }

    public static void removeAvailabe(Line line) {
        if (isLineAvailable(line)) {
            availableLines.remove(line);
            addDrawnLine(line);
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

    public static boolean drawLine(int x1, int y1, int x2, int y2) {
        int xStart, xFinish, yStart, yFinish;
        if (x1 == x2) {
            xStart = x1;
            xFinish = xStart;
            yStart = Math.min(y1, y2);
            yFinish = Math.max(y1, y2);
        } else {
            yStart = y1;
            yFinish = yStart;
            xStart = Math.min(x1, x2);
            xFinish = Math.max(x1, x2);
        }
        if (((Math.abs(xStart - xFinish) == 1) && (yStart == yFinish)) || ((Math.abs(yStart - yFinish)) == 1 && xStart == xFinish)) {
            Dot startDot = Dot.getDotByPosition(xStart, yStart);
            Dot finishDot = Dot.getDotByPosition(xFinish, yFinish);
            Line line = getLineByDots(startDot, finishDot);
            if (isLineAvailable(line)) {
                drawnLines.add(line);
                removeAvailabe(line);
                return true;
            } else {
                ViewDotsAndBox.showErrors(3);
                return false;
            }
        } else {
            ViewDotsAndBox.showErrors(3);
            return false;
        }
    }

    public static Line getLineByDots(Dot dot1, Dot dot2) {
        for (Line line : lines) {
            if (line.getStartDot().equals(dot1)) {
                if (line.getFinishDot().equals(dot2)) {
                    return line;
                }
            } else if (line.getFinishDot().equals(dot2)) {
                if (line.getStartDot().equals(dot1)) {
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
        for (Line drawnLine : drawnLines) {
            if (drawnLine.getStartDot().equals(dot1)) {
                if (drawnLine.getFinishDot().equals(dot2)) {
                    return true;
                }
            } else if (drawnLine.getStartDot().equals(dot2)) {
                if (drawnLine.getFinishDot().equals(dot1)) {
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
