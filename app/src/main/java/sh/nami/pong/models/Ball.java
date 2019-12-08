package sh.nami.pong.models;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import sh.nami.pong.Constants;
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

    private boolean didCollide(Player p) {
        if ((this.position.getX() + this.getWidth()) == p.getPosition().getX()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update() {
        Player p1 = Service.getInstance().state.getPlayer(1);
        Player p2 = Service.getInstance().state.getPlayer(2);

        if (this.direction.getX() > 0) {
            if (this.position.getX() + this.getImage().getWidth() < p2.getPosition().getX()) {
                this.position.add(this.direction.mult(this.velocity));
            } else if(this.position.getX() + this.getImage().getWidth() >= p2.getPosition().getX()) {
                Log.i("P2 Collision", "Collided");
                Hit hit = new Hit(this.direction.reflectX());
                Service.getInstance().hitBall(hit);
            }
        } else if(this.direction.getX() < 0) {
            if (this.position.getX() > p1.getPosition().getX() + p1.getWidth()) {
                this.position.add(this.direction.mult(this.velocity));
            } else if(this.position.getX() <= p1.getPosition().getX() + p1.getWidth()) {
                Log.i("P2 Collision", "Collided");
                Hit hit = new Hit(this.direction.reflectX());
                Service.getInstance().hitBall(hit);
            }
        }

//        if (this.direction.getX() < 0) {
//            boolean p1Collision = this.didCollide(p1);
//
//            if (this.position.getX() > p1.getPosition().getX() + p1.getImage().getWidth()) {
//                this.position.add(this.direction.mult(this.velocity));
//            } else {
//                Log.i("P1 Collision", p1Collision ? "Collided" : "Nope");
//                Hit hit = new Hit(this.direction.reflectX());
//                Service.getInstance().hitBall(hit);
//            }
//        }
    }
}
