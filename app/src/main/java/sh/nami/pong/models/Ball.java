package sh.nami.pong.models;


import android.content.res.Resources;
import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import sh.nami.pong.Constants;
import sh.nami.pong.sprites.BallSprite;

public class Ball extends BallSprite {

    enum BallState
    {
        HIT, MISS, MOTION, BOUNCE
    }

    @SerializedName("velocity")
    private int velocity;
    @SerializedName("direction")
    private NVector direction;

    public Ball(Bitmap image, Vector position, NVector direction) {
        super(image);

        this.setPosition(position);
        this.direction = direction;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public void setDirection(NVector nvector) {
        this.direction = nvector;
    }

    public int getVelocity() {
        return this.velocity;
    }


    private BallState didCollide(Player p) {
        final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        if ((this.position.getX() + this.getWidth() / 2) == p.getPosition().getX() && this.position.getY() >= p.getPosition().getY() && this.position.getY() <= (p.getPosition().getY() + Constants.PADDLE_HEIGHT)) {
            // Ball hit left paddle
            return BallState.HIT;
        } else if ((this.position.getX() - this.getWidth() / 2) == 0) {
            // Left wall collision
            return BallState.MISS;
        } else if ((this.position.getX() + this.getWidth() / 2) >= screenWidth) {
            // Right wall collision
            return BallState.MISS;
        } else if ((this.position.getY() - this.getHeight() / 2) == 0) {
            // Top wall collision
            return BallState.BOUNCE;
        } else if ((this.position.getY() + this.getHeight() / 2) >= screenHeight) {
            // Bottom wall collision
            return BallState.BOUNCE;
        } else {
            // Still in motion
            return BallState.MOTION;
        }
    }

    @Override
    public void update() {

    }
}
