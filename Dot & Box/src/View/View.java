package View;

import Controller.Controller;
import Model.*;

import java.util.*;

public class View {

    public View() {

    }

    public static void getCommand(Scanner scanner) {
        String command;
        while (Line.getAvailableLines().size() != 0) {
            command = scanner.nextLine();
            Controller.run(command);
        }
    }

    public void showAvailableDots() {

    }


}
