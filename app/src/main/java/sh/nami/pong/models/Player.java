package sh.nami.pong.models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import sh.nami.pong.sprites.PaddleSprite;

public class Player extends PaddleSprite {

    @SerializedName("moniker")
    private String moniker;

    @SerializedName("score")
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

    @Override
    public void update(){
    }
}
