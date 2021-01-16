package Model;

public class GameLog {
    private int playedCount;
    private int wins;
    private int score;
    private String gameName;

    {
        playedCount = 0;
        wins = 0;
        score = 0;
    }

    public GameLog(String gameName) {
        this.gameName = gameName;
    }

    public int getWins() {
        return wins;
    }

    public int getScore() {
        return score;
    }

    public int getPlayedCount() {
        return playedCount;
    }

    public String getGameName() {
        return gameName;
    }

    public void incrementGamePlayedCount() {
        this.playedCount++;
    }

    public void incrementWins() {
        this.wins++;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
