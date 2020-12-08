package Controller ;
import java.security.AllPermission;
import java.util.*;
import Model.Game;
import View.*;
public class Controller {
    private static Game game = new Game();
    private static View view = new View(game);


    public Controller() {
    }
}
