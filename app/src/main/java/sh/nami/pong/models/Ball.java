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
        int newX = position.getX() + direction.getX();
        int newY = position.getY() + direction.getY();
        if(newX == //hit) {
            //if(hit == upper wall) {
                // newY = -newY
            //if(hit == right wall) {
                // newX = -newX
                // newY = -newY
            //if(hit == lower wall) {
                // newX = -newX



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
