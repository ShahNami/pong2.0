package sh.nami.pong.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import sh.nami.pong.sprites.PaddleSprite;

public class Player extends PaddleSprite {

    @SerializedName("moniker")
    private String moniker;

    @SerializedName("score")
    private int score = 0;

    private Vector newPosition;

    private int velocity = 50;

    public Player(Bitmap image, String moniker, Vector position) {
        super(image);

        this.moniker = moniker;

        this.setPosition(position);
        this.newPosition = position;
    }

    public String getMoniker() {
        return this.moniker;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public void update(){
        if (this.getPosition().getY() + this.velocity < this.newPosition.getY()) {
            NVector direction = new NVector(0, 1);
            this.getPosition().add(direction.mult(velocity));
        } else if (this.getPosition().getY() - this.velocity > this.newPosition.getY()) {
            NVector direction = new NVector(0, -1);
            this.getPosition().add(direction.mult(velocity));
        }
    }

    public void move(Vector vector) {
        this.newPosition = vector;
    }

    public Rect getBounds() {
        return new Rect(this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getX() + this.getWidth(), this.getPosition().getY() + this.getHeight());
    }

}
