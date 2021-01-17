package Graphic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PlayersData {
    SimpleStringProperty playerName;
    SimpleIntegerProperty ID;

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

    public void setModified(int ID) {
        this.ID.set(ID);
    }

    @Override
    public String toString() {
        return "PlayersData{" +
                "playerName=" + playerName +
                ", ID=" + ID +
                '}';
    }
}