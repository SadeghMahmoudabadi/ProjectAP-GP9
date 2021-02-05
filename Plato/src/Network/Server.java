package Network;

import Controller.Controller;
import Model.Admin;
import Model.Database;
import Model.Player;
import Model.Tools;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.ToLongBiFunction;

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
                    if (input.equalsIgnoreCase("getCurrentPlayer")) {
                        String response;
                        response = new Gson().toJson(Player.getCurrentPlayer());
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                    } else if (input.equalsIgnoreCase("getCurrentAdmin")) {
                        String response;
                        response = new Gson().toJson(Admin.getCurrentAdmin());
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                    } else if (input.equalsIgnoreCase("getPlayers")) {
                        String response = new Gson().toJson(Player.getPlayers());
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                    } else {
                        String response = null;
                        Type type = new TypeToken<String[]>() {
                        }.getType();
                        Gson gson = new Gson();
                        String[] userInput = gson.fromJson(input, type);
                        String controller = userInput[0];
                        String[] correctedUserInput = new String[userInput.length - 1];
                        for (int i = 0; i < correctedUserInput.length; i++) {
                            correctedUserInput[i] = userInput[i + 1];
                        }
                        if (controller.equalsIgnoreCase("logister")) {
                            if (Controller.logisterMenu(correctedUserInput)) {
                                response = "true";
                            } else {
                                response = "false";
                            }
                        } else if (controller.equalsIgnoreCase("user")) {
                            int currentUserID;
                            try {
                                currentUserID = Player.getCurrentPlayer().getUserID();
                            } catch (NullPointerException e) {
                                currentUserID = Admin.getCurrentAdmin().getUserID();
                            }
                            if (Controller.userMenu(currentUserID, correctedUserInput)) {
                                response = "true";
                            } else {
                                response = "false";
                            }
                        } else if (controller.equalsIgnoreCase("player")) {
                            int currentUserID = Player.getCurrentPlayer().getUserID();
                            if (Controller.playerMenu(currentUserID, correctedUserInput)) {
                                response = "true";
                            } else {
                                response = "false";
                            }
                        } else if (controller.equalsIgnoreCase("admin")) {
                            System.out.println("I'm in admin server");
                            int currentUserID = Admin.getCurrentAdmin().getUserID();
                            if (Controller.adminMenu(currentUserID, correctedUserInput)) {
                                response = "true";
                            } else {
                                response = "false";
                            }
                        } else if (controller.equalsIgnoreCase("tools")) {
                            if (correctedUserInput[0].equalsIgnoreCase("isAdmin")) {
                                String username = correctedUserInput[1];
                                if (Tools.isAdmin(username)) {
                                    response = "true";
                                } else {
                                    response = "false";
                                }
                            }
                        }
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                    }
                }
            } catch (Exception e) {
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