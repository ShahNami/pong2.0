package sh.nami.pong.models;


import android.graphics.Bitmap;
import android.graphics.Rect;

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
    // https://stackoverflow.com/questions/21089959/detecting-collision-of-rectangle-with-circle
    private boolean didCollide(Rect p) {
        int r = this.getWidth() / 2;
        int distX = Math.abs(this.getCenterX() - p.left-p.width()/2);
        int distY = Math.abs(this.getCenterY() - p.top-p.height()/2);

        if (distX > (p.width()/2 + r)) { return false; }
        if (distY > (p.height()/2 + r)) { return false; }

        if (distX <= (p.width()/2)) { return true; }
        if (distY <= (p.height()/2)) { return true; }

        int dx=distX-p.width()/2;
        int dy=distY-p.height()/2;
        return (dx*dx+dy*dy<=(r*r));
    }

    private BallState didCollide(Player p1, Player p2) {

        // Headed towards Player 2
        if (this.direction.getX() > 0) {
            if(didCollide(p2.getBounds())) {
                return BallState.HIT;
            } else if(this.getPosition().getX() > p2.getPosition().getX()) {
                return BallState.MISS;
            }
        // Headed towards Player 1
        } else if(this.direction.getX() < 0) {
            if(didCollide(p1.getBounds())) {
                return BallState.HIT;
            } else if(this.getPosition().getX() < p1.getPosition().getX()) {
                return BallState.MISS;
            }
        }
        return BallState.MOTION;
    }

    private void collideHandler(BallState state) {
        Player p1 = Service.getInstance().state.getPlayer(1);
        Player p2 = Service.getInstance().state.getPlayer(2);

        switch(state) {
            case HIT:
                Hit hit = new Hit(this.direction.reflectX());
                Service.getInstance().hitBall(hit);
                break;
            case MISS:
                Miss miss = new Miss(p1, this.direction.reflectX());
                if(this.direction.getX() < 0) {
                    miss = new Miss(p2, this.direction.reflectX());
                }
                Service.getInstance().missBall(miss);
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
        this.position.add(this.direction.mult(this.velocity));

    }

    private int getCenterX() {
        return this.getPosition().getX() + this.getWidth() / 2;
    }

    private int getCenterY() {
        return this.getPosition().getY() + this.getHeight() / 2;
    }
}
