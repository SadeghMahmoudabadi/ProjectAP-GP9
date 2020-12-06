package Model;

public class Player {
    private String user;
    private int score;
    private int wins;

    {
        score = 0;
    }

    public Player(String user, int wins) {
        this.user = user;
        this.wins = wins;
    }

    public int getWins() {
        return wins;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }
}
