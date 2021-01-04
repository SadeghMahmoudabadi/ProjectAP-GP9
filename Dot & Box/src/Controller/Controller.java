package Controller;

import Model.Dot;
import Model.Game;
import Model.Line;
import View.ViewDotsAndBox;


public class Controller {
    private static Game game = new Game();
    private static ViewDotsAndBox viewDotsAndBox = new ViewDotsAndBox(game);


    public Controller() {
    }

    public static void run(String command) {
        if (command.equalsIgnoreCase("start dots and boxes game")) {
            Game.startTheGame();
        } else if (command.startsWith("draw line between")) {
            int x1, y1, x2, y2;
            String[] input = command.split("\\s");
            String startDotCmd = input[3];
            String finishDotCmd = input[5];
            String[] startDotRegex = startDotCmd.split("");
            String[] finishDotRegex = finishDotCmd.split("");
            String xStarChar = startDotRegex[1];
            x1 = Integer.parseInt(xStarChar);
            String yStartChar = startDotRegex[3];
            y1 = Integer.parseInt(yStartChar);
            String xFinishChar = finishDotRegex[1];
            x2 = Integer.parseInt(xFinishChar);
            String yFinishChar = finishDotRegex[3];
            y2 = Integer.parseInt(yFinishChar);
            if (x1 > 8 || x1 < 1 || y1 > 8 || y1 < 1 || x2 > 8 || x2 < 1 || y2 > 8 || y2 < 1) {
                ViewDotsAndBox.showErrors(2);
            } else {
                boolean isLineDrawn = Line.drawLine(x1, y1, x2, y2);
                if (isLineDrawn) {
                    int xStart, yStart, xFinish, yFinish;
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
                    Dot startDot = Dot.getDotByPosition(xStart, yStart);
                    Dot finishDot = Dot.getDotByPosition(xFinish, yFinish);
                    Line line = Line.getLineByDots(startDot, finishDot);
                    game.setLineDrawn(true);
                    game.checkTable(line);
                    if (game.isLineDrawn()) {
                        game.changeTurn();
                        game.setLineDrawn(false);
                    }
                    viewDotsAndBox.showTable();
                }
            }
        } else if (command.equalsIgnoreCase("show available lines")) {
            viewDotsAndBox.showAvailableLines();
        } else if (command.equalsIgnoreCase("show table")) {
            viewDotsAndBox.showTable();
        } else if (command.equalsIgnoreCase("who is next?")) {
            viewDotsAndBox.showWhoIsNext();
        } else if (command.equalsIgnoreCase("show result")) {
            viewDotsAndBox.showResult();
        } else if (command.equalsIgnoreCase("show score")) {
            viewDotsAndBox.showScore();
        }

    }
}
