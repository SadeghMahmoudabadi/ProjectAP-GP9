package Graphic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class PlayersData {
    private SimpleStringProperty playerName;
    private SimpleIntegerProperty ID;

    PlayersData(String playerName, int ID) {
        this.playerName = new SimpleStringProperty(playerName);
        this.ID = new SimpleIntegerProperty(ID);
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public void setPlayerName(String ID) {
        playerName.set(ID);
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }
}