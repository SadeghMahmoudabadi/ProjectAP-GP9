package Model;
import java.util.*;
 public class Game {
    private Player p1;
    private Player p2;
    private int turn;
    private int color ;
    private Grid grid;
    private Scanner scanner;
    Random generator = new Random();

    //new
    public Game() {

    }

    //new
    public void setP1(Player p1) {
        this.p1 = p1;
    }

    //new
    public void setP2(Player p2) {
        this.p2 = p2;
    }

    //new
    public Player getP1() {
        return p1;
    }

    //new
    public Player getP2() {
        return p2;
    }

    //from UML
    public void placeDisk(int color, int x, int y) {

    }


    //from UML
    public void changeColor(int color) {

    }

    //from UML
    public void changeTurn() {
        if (turn == 0) {
            turn = 1;
        } else
            turn = 0;
    }

    //from UML
    public Player whoIsTurn() {
        if (turn == 1) {
            return p1;
        } else
            return p2;
    }


    //from UML
    public void checkGrid() {

    }

    //new
    public static void startTheGame() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                System.out.print("\u26AB    ");
                Coordinate c = new Coordinate(i, j);
                c.addCoordinate(i, j, c);
                if((i == 4 && j ==4) || (i==5 && j==5) ){
                    c.setColor(1);
                }
                if((i == 4 && j ==5) || (i==5 && j==4) ){
                    c.setColor(0);
                }
            }
            System.out.println();
            System.out.println();
        }

    }
    // method for color setting needed
     public void setTurn(){
         int win1 = p1.getWins();
         int win2 = p2.getWins();
         if (win1 > win2) {
             turn = 0;
         } else if (win1 < win2) {
             turn = 1;
         } else {
             turn = generator.nextInt(2);
         }

     }

}