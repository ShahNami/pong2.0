package sh.nami.pong.models;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import sh.nami.pong.Constants;
import sh.nami.pong.R;
import sh.nami.pong.sprites.BallSprite;

public class Ball extends BallSprite {

    enum BallState
    {
        HIT, MISS, MOTION, BOUNCE
    }


    @Override
    public void update() {
        this.position.add(this.direction);
        if(this.getImage() == null) {
            Log.e("Update Ball", "Image is null");
            Bitmap ball = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ball);
            this.setImage(ball);
        }
    }

    private Vector position;
    private int velocity;
    private NVector direction;

    public Ball(Vector position, NVector direction) {
        super(null);
        this.position = position;
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
}
