package Graphic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class FriendsData {
    private SimpleStringProperty friendName;
    private SimpleIntegerProperty ID;
    private Button sendFriendRequest;
    private Button acceptRequest;
    private Button declineRequest;

    FriendsData(String friendName, int ID, Button sendFriendRequest, Button acceptRequest, Button declineRequest) {
        this.friendName = new SimpleStringProperty(friendName);
        this.ID = new SimpleIntegerProperty(ID);
        this.sendFriendRequest = sendFriendRequest;
        this.acceptRequest = acceptRequest;
        this.declineRequest = declineRequest;
    }

    public String getFriendName() {
        return friendName.get();
    }

    public void setFriendName(String ID) {
        friendName.set(ID);
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public Button getSendFriendRequest() {
        return sendFriendRequest;
    }

    public void setSendFriendRequest(Button sendFriendRequest) {
        this.sendFriendRequest = sendFriendRequest;
    }

    public Button getAcceptRequest() {
        return acceptRequest;
    }

    public void setAcceptRequest(Button acceptRequest) {
        this.acceptRequest = acceptRequest;
    }

    public Button getDeclineRequest() {
        return declineRequest;
    }

    public void setDeclineRequest(Button declineRequest) {
        this.declineRequest = declineRequest;
    }
}