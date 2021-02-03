package Network;

import Model.Errors;
import Model.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Client extends Application {
    public static ClientImp clientImp = new ClientImp();
    public static Player currentPlayer;
    public static ArrayList<Player> players;

    public static void main(String[] args) {
        if (clientImp.run()) {
            launch(args);
        }
    }

    @Override
    public void start(Stage loginStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Graphic/loginFX.fxml"));
        loginStage.setScene(new Scene(root, 747, 616));
        loginStage.show();
    }

    public static boolean clientHandle(String[] userInput) {
        return clientImp.handleConnection(userInput);
    }

    public static ArrayList<Player> getPlayers() {
        players = clientImp.getPlayersImp();
        System.out.println();
        return players;
    }

    static class ClientImp {
        private Socket clientSocket;
        DataOutputStream dataOutputStream;
        DataInputStream dataInputStream;

        public boolean handleConnection(String[] userInput) {
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                String input = new Gson().toJson(userInput);
                dataOutputStream.writeUTF(input);
                dataOutputStream.flush();
                String response = dataInputStream.readUTF();
                if (!response.equalsIgnoreCase("false")) {
                    Type type = new TypeToken<Player>() {
                    }.getType();
                    Gson gson = new Gson();
                    currentPlayer = gson.fromJson(response, type);
                    return true;
                } else {
                    Errors.USER_OR_PASS_DOES_NOT_EXIST.showMessage();
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        public ArrayList<Player> getPlayersImp() {
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                dataOutputStream.writeUTF("getPlayers");
                dataOutputStream.flush();
                String response = dataInputStream.readUTF();
                Type type = new TypeToken<ArrayList<Player>>() {
                }.getType();
                Gson gson = new Gson();
                ArrayList<Player> players = gson.fromJson(response, type);
                return players;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public boolean run() {
            try {
                clientSocket = new Socket("127.0.0.1", 8080);
                System.out.println("Successfully connected to server!");
                return true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                exit(0);
                return false;
            }
        }
    }
}