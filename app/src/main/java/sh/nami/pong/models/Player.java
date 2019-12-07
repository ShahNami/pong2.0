package sh.nami.pong.models;


import android.graphics.Bitmap;

import sh.nami.pong.sprites.PaddleSprite;

public class Player extends PaddleSprite {

    private String moniker;
    private int score = 0;

    public Player(Bitmap image, String moniker, Vector position) {
        super(image);
        this.moniker = moniker;
        this.setPosition(position);
    }

    public String getMoniker() {
        return this.moniker;
    }

    public int getScore() {
        return this.score;
    }

}
