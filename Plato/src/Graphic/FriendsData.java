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
    private Button removeFriend;
    private Button personalInfo;

    FriendsData(String friendName, int ID, Button sendFriendRequest, Button acceptRequest, Button declineRequest, Button removeFriend, Button personalInfo) {
        this.friendName = new SimpleStringProperty(friendName);
        this.ID = new SimpleIntegerProperty(ID);
        this.sendFriendRequest = sendFriendRequest;
        this.acceptRequest = acceptRequest;
        this.declineRequest = declineRequest;
        this.removeFriend = removeFriend;
        this.personalInfo = personalInfo;
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

    public Button getRemoveFriend() {
        return removeFriend;
    }

    public void setRemoveFriend(Button removeFriend) {
        this.removeFriend = removeFriend;
    }

    public SimpleStringProperty friendNameProperty() {
        return friendName;
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public Button getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(Button personalInfo) {
        this.personalInfo = personalInfo;
    }
}