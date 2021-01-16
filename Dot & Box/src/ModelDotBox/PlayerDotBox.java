package ModelDotBox;

import javafx.scene.paint.Color;

public class PlayerDotBox {
    private String user;
    private int score;
    //private int wins;

    {
        score = 0;
    }

    public PlayerDotBox(String user/*, int wins*/) {
        this.user = user;
        //this.wins = wins;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    /*public int getWins() {
        return wins;
    }*/

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }
}
