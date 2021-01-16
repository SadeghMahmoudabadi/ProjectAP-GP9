package ModelDotBox;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Box {
    private Dot dot1;
    private Dot dot2;
    private Dot dot3;
    private Dot dot4;
    private static ArrayList<Box> boxes = new ArrayList<>();

    public Box(Dot dot1, Dot dot2, Dot dot3, Dot dot4) {
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.dot3 = dot3;
        this.dot4 = dot4;
    }

    public static void addBox(Box box) {
        boxes.add(box);
    }

    public static int howManyBoxesMade(Line line) {
        int counter = 0;
        if (line.getDirection().equalsIgnoreCase("Horizontal")) {
            if (line.getyStart() > 1) {
                Dot upStartDot = Dot.getDotByPosition(line.getxStart(), line.getyStart() - 1);
                Dot upFinishDot = Dot.getDotByPosition(line.getxFinish(), line.getyFinish() - 1);
                if (Line.isConnected(line.getStartDot(), upStartDot)) {
                    if (Line.isConnected(line.getFinishDot(), upFinishDot)) {
                        if (Line.isConnected(upStartDot, upFinishDot)) {
                            counter++;
                        }
                    }
                }
            }
            if (line.getyStart() < 8) {
                Dot downStartDot = Dot.getDotByPosition(line.getxStart(), line.getyStart() + 1);
                Dot downFinishDot = Dot.getDotByPosition(line.getxFinish(), line.getyFinish() + 1);
                if (Line.isConnected(line.getStartDot(), downStartDot)) {
                    if (Line.isConnected(line.getFinishDot(), downFinishDot)) {
                        if (Line.isConnected(downStartDot, downFinishDot)) {
                            counter++;
                        }
                    }
                }
            }
        } else {
            if (line.getxStart() > 1) {
                Dot leftStartDot = Dot.getDotByPosition(line.getxStart() - 1, line.getyStart());
                Dot leftFinishDot = Dot.getDotByPosition(line.getxFinish() - 1, line.getyFinish());
                if (Line.isConnected(line.getStartDot(), leftStartDot)) {
                    if (Line.isConnected(line.getFinishDot(), leftFinishDot)) {
                        if (Line.isConnected(leftStartDot, leftFinishDot)) {
                            counter++;
                        }
                    }
                }
            }
            if (line.getxStart() < 8) {
                Dot rightStartDot = Dot.getDotByPosition(line.getxStart() + 1, line.getyStart());
                Dot rightFinishDot = Dot.getDotByPosition(line.getxFinish() + 1, line.getyFinish());
                if (Line.isConnected(line.getStartDot(), rightStartDot)) {
                    if (Line.isConnected(line.getFinishDot(), rightFinishDot)) {
                        if (Line.isConnected(rightStartDot, rightFinishDot)) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }


}
