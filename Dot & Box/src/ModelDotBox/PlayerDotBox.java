package ModelDotBox;

import Model.Player;

public class PlayerDotBox {
    private String user;
    private int score;
    private int wins;
    private Player mainPlayer;

    {
        score = 0;
    }

    public PlayerDotBox(String user, int wins, Player mainPlayer) {
        this.mainPlayer = mainPlayer;
        this.user = user;
        this.wins = wins;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    public int getWins() {
        return wins;
    }

    public int getScore() {
        return score;
    }

    public Player getMainPlayer() {
        return mainPlayer;
    }

    public void incrementScore() {
        this.score++;
    }
}
