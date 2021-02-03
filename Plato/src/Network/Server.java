package Network;

import Controller.Controller;
import Model.Database;
import Model.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {
        Database.readFiles();
        new ServerImp().run();
    }

    static class ServerImp {
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private ServerSocket serverSocket;
        private Socket clientSocket;

        public void waitForClient() {
            System.out.println("waiting for client...");
            try {
                clientSocket = serverSocket.accept();
                dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                System.out.println("successfully connected");
            } catch (IOException e) {
                System.err.println("Error connecting client to server!");
            }
        }

        public void handleClient() {
            try {
                while (true) {
                    String input = dataInputStream.readUTF();
                    System.out.println("Client send:" + input);
                    if (input.equalsIgnoreCase("getPlayers")) {
                        String response = new Gson().toJson(Player.getPlayers());
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                    } else {
                        Type type = new TypeToken<String[]>() {
                        }.getType();
                        Gson gson = new Gson();
                        String[] userInput = gson.fromJson(input, type);
                        String controller = userInput[0];
                        String[] correctedUserInput = new String[userInput.length - 1];
                        for (int i = 0; i < userInput.length - 1; i++) {
                            correctedUserInput[i] = userInput[i + 1];
                        }
                        input = new Gson().toJson(correctedUserInput);
                        if (controller.equalsIgnoreCase("logister")) {
                            String response;
                            if (Controller.logisterMenu(input)) {
                                System.out.println(input);
                                response = new Gson().toJson(Player.getCurrentPlayer());
                            } else {
                                response = "false";
                            }
                            dataOutputStream.writeUTF(response);
                            dataOutputStream.flush();
                        } else if (controller.equalsIgnoreCase("user")) {
                            System.out.println(input);
                            String response;
                            int currentUserID = Player.getCurrentPlayer().getUserID();
                            if (Controller.userMenu(currentUserID, input)) {
                                response = "true";
                            } else {
                                response = "false";
                            }
                            dataOutputStream.writeUTF(response);
                            dataOutputStream.flush();
                        } else if (controller.equalsIgnoreCase("player")) {
                            System.out.println(input);
                            String response;
                            int currentUserID = Player.getCurrentPlayer().getUserID();
                            if (Controller.playerMenu(currentUserID, input)) {
                                response = "true";
                            } else {
                                response = "false";
                            }
                            dataOutputStream.writeUTF(response);
                            dataOutputStream.flush();
                    }/* else if (controller.equalsIgnoreCase("admin")) {
                        return Controller.adminMenu(currentUserID, input);
                    }*/
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } finally {
                Database.updateFiles();
            }
        }

        public void run() {
            try {
                System.out.println("trying to connect to server...");
                serverSocket = new ServerSocket(8080);
                System.out.println("Server Initialized!");
                while (true) {
                    waitForClient();
                    handleClient();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}