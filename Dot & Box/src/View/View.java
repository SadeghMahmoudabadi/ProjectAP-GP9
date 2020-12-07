package View;

import Controller.Controller;
import Model.*;

import java.util.*;

public class View {
    private static Scanner scanner;

    {
        scanner = new Scanner(System.in);
    }

    public View() {

    }

    public static void getCommand() {
        String command;
        while (true/*Line.getAvailableLines().size() != 0*/) {
            command = scanner.nextLine();
            Controller.run(command);
        }
    }

    public void showAvailableDots() {

    }


}
