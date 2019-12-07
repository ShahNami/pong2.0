package sh.nami.pong.models;

public class Player {

    private String moniker;
    private int y;
    private int score = 0;

    public Player(String moniker, int y) {
        this.moniker = moniker;
        this.y = y;
    }

    public String getMoniker() {
        return this.moniker;
    }

    public int getY() {
        return this.y;
    }

    public int getScore() {
        return this.score;
    }

    private void move(int y) {
        this.y += y;
    }

}
