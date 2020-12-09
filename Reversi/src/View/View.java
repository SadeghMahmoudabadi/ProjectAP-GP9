package View ;
import Controller.Controller;
import Model.*;
import java.util.*;
public class View {
    private static Scanner scanner = new Scanner(System.in);
    private Game game;

    public View(Game game) {
        this.game = game;
    }

    public static void getCommand() {
        String command;
        while (true/*Line.getAvailableLines().size() != 0*/) {     /*****************/
            command = scanner.nextLine();
            Controller.run(command);
        }


        }
    }
