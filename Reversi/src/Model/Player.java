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

    public String getUser() {
        return user;
    }

    public int getWins() {
        return wins;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        this.score++;
    }
}
