package sh.nami.pong.models;


import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import sh.nami.pong.babble.Service;
import sh.nami.pong.sprites.BallSprite;

public class Ball extends BallSprite {

    enum BallState
    {
        HIT, MISS, MOTION, BOUNCE
    }

    @SerializedName("velocity")
    private int velocity = 10;

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


    // https://yal.cc/rectangle-circle-intersection-test/
    private boolean intersects(Rect p) {
        int deltaX = this.getPosition().getX() - Math.max(p.left, Math.min(this.getPosition().getX(), p.right));
        int deltaY = this.getPosition().getY() - Math.max(p.top, Math.min(this.getPosition().getY(), p.bottom));
        return (deltaX * deltaX + deltaY * deltaY) < (this.getWidth() / 2 * this.getHeight() / 2);
    }

    private BallState didCollide(Player p1, Player p2) {

        // Headed towards Player 2
        if (this.direction.getX() > 0) {
            if (this.position.getX() + this.getImage().getWidth() < p2.getPosition().getX()) {
                return BallState.MOTION;
            } else if (this.position.getX() + this.getImage().getWidth() >= p2.getPosition().getX()) {
               return BallState.HIT;
            }
        // Headed towards Player 1
        } else if(this.direction.getX() < 0) {
            if (this.position.getX() > p1.getPosition().getX() + p1.getWidth()) {
                return BallState.MOTION;
            } else if(this.position.getX() <= p1.getPosition().getX() + p1.getWidth() && this.position.getY() <= p1.getPosition().getY() + p1.getHeight() && this.position.getY() >= p1.getPosition().getY()) {
                return BallState.HIT;
            }
        }
        return BallState.MOTION;
    }

    private void collideHandler(BallState state) {
        switch(state) {
            case HIT:
                Hit hit = new Hit(this.direction.reflectX());
                Service.getInstance().hitBall(hit);
                break;
            case MISS:
                break;
            case BOUNCE:
                break;
            case MOTION:
                this.position.add(this.direction.mult(this.velocity));
                break;
            default:
                break;
        }
    }

    @Override
    public void update() {
        Player p1 = Service.getInstance().state.getPlayer(1);
        Player p2 = Service.getInstance().state.getPlayer(2);

        BallState state = this.didCollide(p1, p2);
        this.collideHandler(state);
        Log.i("update", "Ball collided with P2: " + this.intersects(p2.getBounds()));

    }
}
