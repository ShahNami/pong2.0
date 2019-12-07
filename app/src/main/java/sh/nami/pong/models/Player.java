package sh.nami.pong.models;

public class Player {

    private String moniker;
    private Vector position;
    private int score = 0;

    public Player(String moniker, Vector position) {
        this.moniker = moniker;
        this.position = position;
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
