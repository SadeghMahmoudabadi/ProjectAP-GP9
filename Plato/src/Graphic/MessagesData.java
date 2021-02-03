package Graphic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MessagesData {
    private SimpleIntegerProperty ID;
    private SimpleStringProperty message;
    private SimpleStringProperty receiverName;

    public MessagesData(int ID, String message, String receiverName) {
        this.ID = new SimpleIntegerProperty(ID);
        this.message = new SimpleStringProperty(message);
        this.receiverName = new SimpleStringProperty(receiverName);
    }

    public int getID() {
        return ID.get();
    }

    public String getMessage() {
        return message.get();
    }

    public String getReceiverName() {
        return receiverName.get();
    }
}
