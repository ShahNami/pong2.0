package sh.nami.pong.models;


import android.graphics.Canvas;

public class Ball {
    private Vector position;
    private int velocity;
    private NVector direction;

    public Ball(Vector position) {
        this.calculateTrajectory(position);
    }


    public void draw(Canvas canvas) {
        // y = kx + b
        // k = tanr
    }

    public void update() {



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
}
